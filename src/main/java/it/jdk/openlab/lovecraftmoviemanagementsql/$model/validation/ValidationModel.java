package it.jdk.openlab.lovecraftmoviemanagementsql.$model.validation;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Locale;
import java.util.Set;

public abstract class ValidationModel <T, E extends SuperException> {

    private Validator validator;

    public ValidationModel() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public ValidationModel(Validator validator) { this.validator = validator; }

    public void validate(T model, String exceptionMessage, Locale locale, Class<?> group) throws E {
        Set<ConstraintViolation<T>> constraints = validator.validate(model, group);
        Locale.setDefault(locale);
        if(!constraints.isEmpty()) {
            E serviceException = buildException(exceptionMessage);
            for(ConstraintViolation<T> constraint : constraints) {
                String key = constraint.getPropertyPath().toString();
                if(!key.isEmpty()) {
                    if(serviceException.getErrors().containsKey(key)) {
                        String message = serviceException.getErrors().get(key);
                        serviceException.addError(key, message + "," + constraint.getMessage());
                    } else
                        serviceException.addError(key, constraint.getMessage());
                }
            }
            throw serviceException;
        }
    }

    public void validate(T model, String exceptionMessage, Class<?> group) throws E {
        validate(model, exceptionMessage, Locale.getDefault(), group);
    }

    public abstract E buildException(String message);
}
