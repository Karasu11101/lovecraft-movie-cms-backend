package it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.annotations;

import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.validator.MovieNonExistentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MovieNonExistentValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MovieNonExistent {
    public String message() default "A movie with this id is already present on the DB";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
