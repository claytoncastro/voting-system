package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.dto.client.feign.PautaGetFeignClientResponse;
import br.com.project.msvotacao.dto.service.PautaVotacao;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import br.com.project.msvotacao.exception.feign.FeignExceptionService;
import br.com.project.msvotacao.service.PautaVotacaoService;
import br.com.project.msvotacao.service.client.feign.PautaFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaVotacaoServiceImpl implements PautaVotacaoService {

    private final PautaFeignClient pautaFeignClient;
    private final FeignExceptionService feignExceptionService;

    @Override
    public PautaVotacao obterPautaVotacao() throws ErroComunicacaoMicroservicesException {
        try {
            PautaGetFeignClientResponse pautaGetClientResponse = pautaFeignClient.obterPautaAberta();
            return PautaVotacao.from(pautaGetClientResponse);

        } catch (FeignException.FeignClientException | FeignException.ServiceUnavailable e) {
            feignExceptionService.parseException(e);
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), e.status());
        }
    }

    @Override
    public void encerrarPautaAberta() throws ErroComunicacaoMicroservicesException {
        try {
            pautaFeignClient.encerrarPautaAberta();
        } catch (FeignException.FeignClientException | FeignException.ServiceUnavailable e) {
            feignExceptionService.parseException(e);
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), e.status());
        }
    }

}
