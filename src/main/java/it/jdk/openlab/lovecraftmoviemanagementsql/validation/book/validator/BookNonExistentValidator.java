package it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.validator;

import it.jdk.openlab.lovecraftmoviemanagementsql.repository.book.BookSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.annotations.BookNonExistent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("bookNonExistentValidator")
public class BookNonExistentValidator  implements ConstraintValidator<BookNonExistent, Integer> {

    private final BookSpringDataRepository bookRepository;

    @Autowired
    public BookNonExistentValidator(BookSpringDataRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return !this.bookRepository.findById(id).isPresent();
    }

    @Override
    public void initialize(BookNonExistent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
