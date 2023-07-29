package it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.annotations;

import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.validator.MovieExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MovieExistsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MovieExists {
    public String message() default "A movie with this id does not exist";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
