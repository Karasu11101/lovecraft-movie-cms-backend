package it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.validator;

import it.jdk.openlab.lovecraftmoviemanagementsql.repository.book.BookSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.annotations.BookExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("bookExistsValidator")
public class BookExistsValidator implements ConstraintValidator<BookExists, Integer> {

    private final BookSpringDataRepository bookRepository;

    @Autowired
    public BookExistsValidator(BookSpringDataRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void initialize(BookExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return bookRepository.findById(id).isPresent();
    }
}
