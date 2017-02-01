package com.example;

import com.example.security.SecurityLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that can be applied to fields of an object. When the annotated object is passed to the Redactor
 * class it inspects for the use of this annotation. Any fields that the Redactor finds annotated with @Redactable it
 * changes the value to NULL unless the allowSecurityLevel property has been set AND the Redactor has been passed the
 * a SecurityLevel enum which is also in the allowSecurityLevel propery on the annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Redactable {

    SecurityLevel[] allowSecurityLevel() default {};

}
