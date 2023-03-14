package br.com.project.msvotacao.exception.handler;

import br.com.project.msvotacao.exception.ResourceNotFoundException;
import br.com.project.msvotacao.exception.ServiceUnavailableException;
import br.com.project.msvotacao.exception.details.ResourceNotFoundDetails;
import br.com.project.msvotacao.exception.details.ServiceUnavailableDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handlerServiceUnavailableException(ServiceUnavailableException suException) {
        ServiceUnavailableDetails serviceUnavailable = ServiceUnavailableDetails.builder()
                .timestamp(new Date().getTime())
                .status(SERVICE_UNAVAILABLE.value())
                .title("Serviço não disponível")
                .detail(suException.getMessage())
                .developerMessage(suException.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(serviceUnavailable, SERVICE_UNAVAILABLE);
    }

}
