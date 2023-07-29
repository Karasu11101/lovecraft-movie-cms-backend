package it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.validator;

import it.jdk.openlab.lovecraftmoviemanagementsql.repository.user.UserSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.annotations.UserExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userExistsValidator")
public class UserExistsValidator implements ConstraintValidator<UserExists, Integer> {

    private final UserSpringDataRepository userRepository;

    @Autowired
    public UserExistsValidator(UserSpringDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return this.userRepository.findById(id).isPresent();
    }

    @Override
    public void initialize(UserExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
