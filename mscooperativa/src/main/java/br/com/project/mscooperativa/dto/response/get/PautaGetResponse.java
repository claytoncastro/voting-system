package br.com.project.mscooperativa.dto.response.get;

import br.com.project.mscooperativa.model.Pauta;
import br.com.project.mscooperativa.model.StatusPauta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaGetResponse {

    private Long id;
    private String tema;
    private StatusPauta status;

    public static PautaGetResponse from(Pauta data) {
        return PautaGetResponse.builder()
                .id(data.getId())
                .tema(data.getTema())
                .status(data.getStatus())
                .build();
    }

}
