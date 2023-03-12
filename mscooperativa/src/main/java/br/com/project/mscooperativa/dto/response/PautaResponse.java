package br.com.project.mscooperativa.dto.response;

import br.com.project.mscooperativa.model.Pauta;
import br.com.project.mscooperativa.model.StatusPauta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaResponse {

    private String tema;
    private StatusPauta status;

    public static PautaResponse from(Pauta data) {
        return PautaResponse.builder()
                .tema(data.getTema())
                .status(data.getStatus())
                .build();
    }

}
