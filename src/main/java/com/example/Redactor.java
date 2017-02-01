package com.example;

import com.example.security.SecurityLevel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.EnumSet;

public class Redactor {

    public static final void redact(final Object object, SecurityLevel securityLevel) throws IllegalAccessException {

        Class<?> objectClass = object.getClass();
        while (objectClass != null && objectClass != Object.class)
        {
            Field[] fields = objectClass.getDeclaredFields();
            for (Field field : fields) {
                // For each field in the object see if it has the @Redactable annotation applied.
                Redactable redactable = field.getDeclaredAnnotation(Redactable.class);
                if (redactable != null) {
                    // Get the allowed security levels for that particular annotated field from the allowSecurityLevel property on the annotation
                    EnumSet<SecurityLevel> levels = redactable.allowSecurityLevel().length == 0 ? EnumSet.noneOf(SecurityLevel.class)
                            : EnumSet.copyOf(Arrays.asList(redactable.allowSecurityLevel()));

                    if (!levels.contains(securityLevel)) {
                        // The redactor was not passed a security level that was in the @Redactable annotation or no security level was
                        // specified so NULL the field.
                        field.setAccessible(true);
                        field.set(object, null);
                    }
                }
            }
            objectClass = objectClass.getSuperclass();
        }
    }
}
