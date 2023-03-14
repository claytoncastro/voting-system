package br.com.project.mscooperativa.service.impl;

import br.com.project.mscooperativa.dto.request.PautaRequest;
import br.com.project.mscooperativa.dto.response.get.PautaGetResponse;
import br.com.project.mscooperativa.dto.response.post.PautaPostResponse;
import br.com.project.mscooperativa.exception.ResourceAlreadyExistException;
import br.com.project.mscooperativa.exception.ResourceNotFoundException;
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
    public PautaPostResponse salvarPauta(PautaRequest pautaRequest) {
        validaSeExistePautaAberta();
        Pauta pautaParaSalvar = new Pauta();
        pautaParaSalvar.setStatus(ABERTA);
        pautaParaSalvar.setTema(pautaRequest.getTema());

        return PautaPostResponse.from(pautaRepository.save(pautaParaSalvar));
    }

    @Override
    public PautaGetResponse obterPautasAbertas() {
        Pauta pautaStatusEqualsAberta = pautaRepository
                .findByStatusEqualsAberta()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma Pauta com status 'ABERTA' foi encontrada."));

        return PautaGetResponse.from(pautaStatusEqualsAberta);
    }

    private void validaSeExistePautaAberta() {
        Optional.ofNullable(pautaRepository.findByStatusEqualsAberta())
                .filter(List::isEmpty)
                .orElseThrow(
                        () -> new ResourceAlreadyExistException("Já existe uma pauta em aberto sendo votada."));
    }

}
