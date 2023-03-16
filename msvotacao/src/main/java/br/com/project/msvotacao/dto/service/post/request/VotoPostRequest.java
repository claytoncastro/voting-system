package br.com.project.msvotacao.dto.service.post.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class VotoPostRequest {

    @NotBlank(message = "O campo 'cpfVotante' não pode ser branco ou nulo!")
    private String cpfVotante;
    @NotNull(message = "O campo 'resposta' não pode ser branco ou nulo!")
    private RespostaVotacao resposta;

}
