package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.dto.amqp.VotacaoPautaRequestPublisher;
import br.com.project.msvotacao.dto.service.PautaVotacao;
import br.com.project.msvotacao.dto.service.post.request.VotoPostRequest;
import br.com.project.msvotacao.dto.service.post.response.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import br.com.project.msvotacao.exception.ResourceAlreadyExistException;
import br.com.project.msvotacao.model.Votacao;
import br.com.project.msvotacao.repository.VotacaoRepository;
import br.com.project.msvotacao.service.PautaVotacaoService;
import br.com.project.msvotacao.service.VotacaoCacheService;
import br.com.project.msvotacao.service.VotacaoSchedulingService;
import br.com.project.msvotacao.service.VotacaoService;
import br.com.project.msvotacao.service.amqp.VotacaoPautaPublisher;
import br.com.project.msvotacao.util.MessageSourceUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.project.msvotacao.model.StatusVotacao.ABERTA;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private final VotacaoRepository votacaoRepository;
    private final VotacaoCacheService votacaoCacheService;
    private final PautaVotacaoService pautaVotacaoService;
    private final VotacaoSchedulingService votacaoSchedulingService;
    private final MessageSourceUtil messageSourceUtil;
    private final VotacaoPautaPublisher votacaoPautaPublisher;

    @Override
    @Transactional
    public VotacaoPostResponse abrirVotacao(long tempoParavotacao) throws ErroComunicacaoMicroservicesException {
        validaSeExisteVotacaoComStatusAberta();
        PautaVotacao pautaVotacao = this.obterPautaVotacao();

        this.salvarVotacao(pautaVotacao);
        // Enviar dado de abertura de votação para serviço mscontabilizador
        Votacao votacao = votacaoCacheService.cacheVotacaoAberta();
        votacaoSchedulingService.scheduleATask(tempoParavotacao);

        return VotacaoPostResponse.from(votacao);
    }

    @Override
    public void votar(VotoPostRequest request) throws JsonProcessingException {
        Votacao votacao = votacaoCacheService.cacheVotacaoAberta();

        if(ABERTA.equals(votacao.getStatusVotacao())) {
            votacaoPautaPublisher.votacaoPauta(
                    VotacaoPautaRequestPublisher
                            .builder()
                            .tipoResposta(request.getResposta().name())
                            .build());
            System.out.println("Você votou!");
        } else {
            // Consome os dados da votação no serviço mscontabilizador
            System.out.println("Votação encerrada!");
        }
    }

    private void validaSeExisteVotacaoComStatusAberta() {
        Optional.of(votacaoRepository.findAll().stream()
                        .filter(votacao -> ABERTA.equals(votacao.getStatusVotacao()))
                        .collect(Collectors.toList()))
                .filter(List::isEmpty)
                .orElseThrow(
                        () -> new ResourceAlreadyExistException(
                                messageSourceUtil.message("api.error.existe.votacao.status.aberta")));
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

}
