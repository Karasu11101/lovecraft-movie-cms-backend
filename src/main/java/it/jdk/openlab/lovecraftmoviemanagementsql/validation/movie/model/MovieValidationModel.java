package it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.model;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.ServiceException;
import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.$model.validation.ValidationModel;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.Movie;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieValidationModel extends ValidationModel<Movie, SuperException> {

    public MovieValidationModel() {}

    @Autowired
    public MovieValidationModel(Validator validator) {
        super(validator);
    }

    @Override
    public SuperException buildException(String message) {
        return new ServiceException(message);
    }
}
