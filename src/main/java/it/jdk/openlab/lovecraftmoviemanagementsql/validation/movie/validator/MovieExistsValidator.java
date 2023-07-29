package it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.validator;

import it.jdk.openlab.lovecraftmoviemanagementsql.repository.movie.MovieSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.annotations.MovieExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("movieExistsValidator")
public class MovieExistsValidator implements ConstraintValidator<MovieExists, Integer> {

    private final MovieSpringDataRepository movieRepository;

    @Autowired
    public MovieExistsValidator(MovieSpringDataRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return this.movieRepository.findById(id).isPresent();
    }

    @Override
    public void initialize(MovieExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
