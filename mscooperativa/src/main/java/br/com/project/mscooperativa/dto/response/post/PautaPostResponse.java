package br.com.project.mscooperativa.dto.response.post;

import br.com.project.mscooperativa.model.Pauta;
import br.com.project.mscooperativa.model.StatusPauta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaPostResponse {

    private String tema;
    private StatusPauta status;

    public static PautaPostResponse from(Pauta data) {
        return PautaPostResponse.builder()
                .tema(data.getTema())
                .status(data.getStatus())
                .build();
    }

}
