package br.com.project.msvotacao.dto.service.response.post;

import br.com.project.msvotacao.dto.service.PautaVotacao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotacaoPostResponse {

    private Long idPauta;
    private String temaPautaVotacao;

    public static VotacaoPostResponse from(PautaVotacao data) {
        return VotacaoPostResponse.builder()
                .idPauta(data.getId())
                .temaPautaVotacao(data.getTema())
                .build();
    }


}
