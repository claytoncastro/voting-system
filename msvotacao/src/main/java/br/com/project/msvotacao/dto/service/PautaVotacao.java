package br.com.project.msvotacao.dto.service;

import br.com.project.msvotacao.dto.client.feign.PautaGetFeignClientResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaVotacao {

    private Long id;
    private String tema;

    public static PautaVotacao from(PautaGetFeignClientResponse data) {
        return PautaVotacao.builder()
                .id(data.getId())
                .tema(data.getTema())
                .build();
    }

}
