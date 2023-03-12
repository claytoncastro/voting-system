package br.com.project.mscooperativa.service.impl;

import br.com.project.mscooperativa.dto.request.PautaRequest;
import br.com.project.mscooperativa.dto.response.PautaResponse;
import br.com.project.mscooperativa.exception.ResourceAlreadyExistException;
import br.com.project.mscooperativa.model.Pauta;
import br.com.project.mscooperativa.repository.PautaRepository;
import br.com.project.mscooperativa.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.project.mscooperativa.model.StatusPauta.ABERTA;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Override
    @Transactional
    public PautaResponse salvarPauta(PautaRequest pautaRequest) {
        validaSeExistePautaAberta();
        Pauta pautaParaSalvar = new Pauta();
        pautaParaSalvar.setStatus(ABERTA);
        pautaParaSalvar.setTema(pautaRequest.getTema());

        return PautaResponse.from(pautaRepository.save(pautaParaSalvar));
    }

    private void validaSeExistePautaAberta() {
        Optional.ofNullable(pautaRepository.findByStatusEqualsAberta())
                .filter(List::isEmpty)
                .orElseThrow(
                        () -> new ResourceAlreadyExistException("Já existe uma pauta em aberto sendo votada."));
    }

}
