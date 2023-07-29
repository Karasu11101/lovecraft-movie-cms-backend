package it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.validator;

import it.jdk.openlab.lovecraftmoviemanagementsql.repository.movie.MovieSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.annotations.MovieNonExistent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("movieNonExistentValidator")
public class MovieNonExistentValidator implements ConstraintValidator<MovieNonExistent, Integer> {

    private final MovieSpringDataRepository movieRepository;

    @Autowired
    public MovieNonExistentValidator(MovieSpringDataRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return !this.movieRepository.findById(id).isPresent();
    }

    @Override
    public void initialize(MovieNonExistent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
