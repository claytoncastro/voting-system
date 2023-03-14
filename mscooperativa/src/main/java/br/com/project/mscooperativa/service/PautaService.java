package br.com.project.mscooperativa.service;

import br.com.project.mscooperativa.dto.request.PautaRequest;
import br.com.project.mscooperativa.dto.response.get.PautaGetResponse;
import br.com.project.mscooperativa.dto.response.post.PautaPostResponse;

public interface PautaService {

    PautaPostResponse salvarPauta(PautaRequest pauta);
    PautaGetResponse obterPautasAbertas();

}
