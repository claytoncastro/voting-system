package br.com.project.mscooperativa.service;

import br.com.project.mscooperativa.dto.request.PautaRequest;
import br.com.project.mscooperativa.dto.response.PautaResponse;

public interface PautaService {

    PautaResponse salvarPauta(PautaRequest pauta);

}
