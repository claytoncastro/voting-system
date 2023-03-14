package br.com.project.msvotacao.exception.feign;

import br.com.project.msvotacao.exception.ResourceNotFoundException;
import br.com.project.msvotacao.exception.ServiceUnavailableException;
import feign.FeignException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Component
@NoArgsConstructor(access = PRIVATE)
public class FeignExceptionUtil {

    public static void parseException(FeignException e) {
        int status = e.status();
        if (NOT_FOUND.value() == status) {
            throw new ResourceNotFoundException("Nenhuma Pauta com status 'ABERTA' para votação foi encontrada.");
        } else if (SERVICE_UNAVAILABLE.value() == status) {
            throw new ServiceUnavailableException("Serviço não diponível.");
        }
    }

}
