package it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.validator;

import it.jdk.openlab.lovecraftmoviemanagementsql.repository.user.UserSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.annotations.UserNonExistent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userNonExistentValidator")
public class UserNonExistentValidator implements ConstraintValidator<UserNonExistent, Integer> {

    private final UserSpringDataRepository userRepository;

    @Autowired
    public UserNonExistentValidator(UserSpringDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userRepository.findById(id).isPresent();
    }

    @Override
    public void initialize(UserNonExistent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
