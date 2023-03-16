package br.com.project.msvotacao.dto.amqp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotacaoPautaRequestPublisher {

    private String tipoResposta;

}
