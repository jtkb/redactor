package com.example;

import com.example.security.SecurityLevel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IllegalAccessException {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        ChildPojo dto = new ChildPojo();
        dto.setSecondName("this is my second name");
        dto.setPublicName("This is my public name.");
        dto.setSecretName("this is my secret name");

        printValidationViolations(validator.validate(dto));

        Redactor.redact(dto, null);

        printOutFields(dto);

        /*
        Reset the secret name but this time pass in a security level that permits viewing of a
        @Redactable at security level TOP SECRET.
         */

        dto.setSecretName("Only to be seen by top secret clearance.");

        Redactor.redact(dto, SecurityLevel.TOP_SECRET);

        printOutFields(dto);

        /*
        Try again with a lower security clearance - should not see the secret name.
         */
        Redactor.redact(dto, SecurityLevel.ANYONE);

        printOutFields(dto);

        printValidationViolations(validator.validate(dto));


    }

    public static void printOutFields(final SimplePojoDto dto)
    {
        System.out.println("Public name: " + dto.getPublicName());
        System.out.println("Secret name: " + dto.getSecretName());
    }

    public static void printValidationViolations(final Set<? extends ConstraintViolation<?>> violations)
    {
        System.out.println("****************** Showing Validation violations *************************");
        if (violations.size() > 0)
        {
            for (ConstraintViolation<?> violation : violations)
            {
                System.out.println(violation.getPropertyPath() + " " +violation.getMessage());
            }
        }
        System.out.println("**************** End of Validation violations **********************");
    }
}
