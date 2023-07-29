package it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.annotations;

import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.validator.BookNonExistentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BookNonExistentValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookNonExistent {
    public String message() default "A book with this id is already present on the DB";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
