package it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.annotations;

import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.validator.UserNonExistentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserNonExistentValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNonExistent {
    public String message() default "A user with this id already exists";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
