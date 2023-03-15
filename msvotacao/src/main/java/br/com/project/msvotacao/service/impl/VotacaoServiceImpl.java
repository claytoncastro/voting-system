package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.dto.service.PautaVotacao;
import br.com.project.msvotacao.dto.service.response.post.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import br.com.project.msvotacao.exception.ResourceAlreadyExistException;
import br.com.project.msvotacao.model.Votacao;
import br.com.project.msvotacao.repository.VotacaoRepository;
import br.com.project.msvotacao.service.PautaVotacaoService;
import br.com.project.msvotacao.service.TaskSchedulingService;
import br.com.project.msvotacao.service.VotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.project.msvotacao.model.StatusVotacao.ABERTA;

@Service
@RequiredArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private final VotacaoRepository votacaoRepository;
    private final PautaVotacaoService pautaVotacaoService;
    private final TaskSchedulingService taskSchedulingService;

    @Override
    @Transactional
    public VotacaoPostResponse abrirVotacao(long tempoParavotacao) throws ErroComunicacaoMicroservicesException {
        validarSeExisteVotacaoStatusAberta();
        PautaVotacao pautaVotacao = obterPautaVotacao();

        salvarVotacao(pautaVotacao);
        taskSchedulingService.scheduleATask(tempoParavotacao);
        return VotacaoPostResponse.from(pautaVotacao);
    }

    private PautaVotacao obterPautaVotacao() throws ErroComunicacaoMicroservicesException {
        return pautaVotacaoService.obterPautaVotacao();
    }

    private void salvarVotacao(PautaVotacao pautaVotacao) {
        votacaoRepository
                .save(
                        Votacao.builder()
                                .tema(pautaVotacao.getTema())
                                .statusVotacao(ABERTA)
                                .build()
                );
    }

    private void validarSeExisteVotacaoStatusAberta() {
        Optional
                .of(votacaoRepository
                        .findAll()
                        .stream()
                        .filter(votacao -> ABERTA.equals(votacao.getStatusVotacao()))
                        .collect(Collectors.toList()))
                .filter(List::isEmpty)
                .orElseThrow(
                        () -> new ResourceAlreadyExistException("Já existe uma votação com status 'ABERTA'."));
    }

}
