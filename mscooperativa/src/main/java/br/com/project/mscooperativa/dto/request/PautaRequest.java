package br.com.project.mscooperativa.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PautaRequest {

    @NotBlank(message = "O campo 'tema' deve ser preenchido!")
    private String tema;

}
