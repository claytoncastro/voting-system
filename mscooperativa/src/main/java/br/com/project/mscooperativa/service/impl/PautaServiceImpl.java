package br.com.project.mscooperativa.service.impl;

import br.com.project.mscooperativa.dto.request.PautaRequest;
import br.com.project.mscooperativa.dto.response.PautaResponse;
import br.com.project.mscooperativa.model.Pauta;
import br.com.project.mscooperativa.repository.PautaRepository;
import br.com.project.mscooperativa.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.project.mscooperativa.model.StatusPauta.ABERTA;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Override
    public PautaResponse salvarPauta(PautaRequest pautaRequest) {
        Pauta pautaParaSalvar = new Pauta();
        pautaParaSalvar.setStatus(ABERTA);
        pautaParaSalvar.setTema(pautaRequest.getTema());

        return PautaResponse.from(pautaRepository.save(pautaParaSalvar));
    }
}
