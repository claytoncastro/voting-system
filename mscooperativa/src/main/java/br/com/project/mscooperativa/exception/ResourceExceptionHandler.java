package br.com.project.mscooperativa.exception;

import br.com.project.mscooperativa.exception.details.ResourceAlreadyExistDetails;
import br.com.project.mscooperativa.exception.details.ResourceNotFoundDetails;
import br.com.project.mscooperativa.exception.details.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Object> handlerResourceAlreadyExistException(ResourceAlreadyExistException raeException) {
        ResourceAlreadyExistDetails resourceNotFound = ResourceAlreadyExistDetails.builder()
                .timestamp(new Date().getTime())
                .status(CONFLICT.value())
                .title("Recurso já existe")
                .detail(raeException.getMessage())
                .developerMessage(raeException.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(resourceNotFound, CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(ResourceNotFoundException rnfException) {
        ResourceNotFoundDetails resourceNotFound = ResourceNotFoundDetails.builder()
                .timestamp(new Date().getTime())
                .status(NOT_FOUND.value())
                .title("Recurso não foi encontrado")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(resourceNotFound, NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));

        ValidationErrorDetails veDetails = ValidationErrorDetails.builder()
                .timestamp(new Date().getTime())
                .status(BAD_REQUEST.value())
                .title("Erro de Validação de campo")
                .detail("Erro no(s) campo(s)")
                .developerMessage(manvException.getClass().getSimpleName())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();
        return new ResponseEntity<>(veDetails, BAD_REQUEST);
    }

}
