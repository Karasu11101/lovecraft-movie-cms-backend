package it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.model;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.ServiceException;
import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.$model.validation.ValidationModel;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.User;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidationModel extends ValidationModel<User, SuperException> {

    public UserValidationModel() {}

    @Autowired
    public UserValidationModel(Validator validator) {
        super(validator);
    }

    @Override
    public SuperException buildException(String message) {
        return new ServiceException(message);
    }
}
