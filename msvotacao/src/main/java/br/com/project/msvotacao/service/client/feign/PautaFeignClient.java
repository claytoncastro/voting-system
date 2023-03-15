package br.com.project.msvotacao.service.client.feign;

import br.com.project.msvotacao.dto.client.feign.PautaGetFeignClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "mscooperativa", path = "/pautas")
public interface PautaFeignClient {

    @GetMapping("/pauta-aberta")
    PautaGetFeignClientResponse obterPautaAberta();

    @PostMapping("/encerrar-pauta")
    void encerrarPautaAberta();

}
