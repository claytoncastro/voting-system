package br.com.project.msvotacao.exception.feign;

import br.com.project.msvotacao.exception.ResourceNotFoundException;
import br.com.project.msvotacao.exception.ServiceUnavailableException;
import br.com.project.msvotacao.util.MessageSourceUtil;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignExceptionService {

    private final MessageSourceUtil messageSourceService;

    public void parseException(FeignException e) {
        switch (HttpStatus.valueOf(e.status())) {
            case NOT_FOUND:
                throw new ResourceNotFoundException(
                        messageSourceService.message("api.error.nenhuma.pauta.status.aberto"));
            case SERVICE_UNAVAILABLE :
                throw new ServiceUnavailableException(
                        messageSourceService.message("api.error.servico.indisponive"));
        }
    }

}
