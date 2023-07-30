package it.jdk.openlab.lovecraftmoviemanagementsql.$exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String[] fields = field.split("\\.");
            String fieldName = field.contains(".") ? fields[fields.length-1] : field;
            errors.put(fieldName, fieldError.getDefaultMessage());
        }
        return badRequest().body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        String error = "Malformed JSON request";
        Map<Object, Object> response = new HashMap<>();
        response.put("error", error + ": " + ex.getRootCause().getMessage());
        return badRequest().body(response);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for(ConstraintViolation<?> constraintViolation : constraintViolations) {
            String fieldName = null;
            Iterator iterator = constraintViolation.getPropertyPath().iterator();
            while(iterator.hasNext())
                fieldName = iterator.next().toString();
            errors.put(fieldName, constraintViolation.getMessage());
        }
        return badRequest().body(errors);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Map<Object, Object>> handleRuntimeException(RuntimeException ex) {
        Map<Object, Object> response = new HashMap<>();
        response.put("error", ex.getStackTrace());
        return internalServerError().body(response);
    }

    @ExceptionHandler(value = {SuperException.class})
    public ResponseEntity<Map<String, String>> handleSuperException(SuperException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrors());
    }
}
