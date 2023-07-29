package it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.annotations;

import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.validator.BookExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BookExistsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookExists {
    public String message() default "A book with this id does not exist";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
