package br.com.project.mscooperativa.exception;

import br.com.project.mscooperativa.exception.details.ResourceAlreadyExistDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(ResourceAlreadyExistException raeException) {
        ResourceAlreadyExistDetails resourceNotFound = ResourceAlreadyExistDetails.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.CONFLICT.value())
                .title("Recurso já existe")
                .detail(raeException.getMessage())
                .developerMessage(raeException.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(resourceNotFound, HttpStatus.NOT_FOUND);
    }

}
