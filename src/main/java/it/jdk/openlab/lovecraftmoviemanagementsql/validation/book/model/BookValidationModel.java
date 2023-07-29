package it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.model;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.ServiceException;
import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.$model.validation.ValidationModel;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.Book;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookValidationModel extends ValidationModel<Book, SuperException> {

    public BookValidationModel() {}

    @Autowired
    public BookValidationModel(Validator validator) {
        super(validator);
    }

    @Override
    public SuperException buildException(String message) {
        return new ServiceException(message);
    }
}
