package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.exception.ResourceNotFoundException;
import br.com.project.msvotacao.model.Votacao;
import br.com.project.msvotacao.repository.VotacaoRepository;
import br.com.project.msvotacao.service.VotacaoDadosCacheService;
import br.com.project.msvotacao.util.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.project.msvotacao.model.StatusVotacao.ABERTA;
import static br.com.project.msvotacao.model.StatusVotacao.ENCERRADA;

@Service
@RequiredArgsConstructor
public class VotacaoDadosCacheServiceImpl implements VotacaoDadosCacheService {

    private final VotacaoRepository votacaoRepository;
    private final MessageSourceUtil messageSourceUtil;

    @Override
    public void encerrarVotacao() {
        Votacao votacao = obterVotacaoStatusAberta();
        votacao.setStatusVotacao(ENCERRADA);

        votacaoRepository.save(votacao);
    }

    @Override
    public Votacao obterVotacaoStatusAberta() {
        Optional<Votacao> optionalVotacao = votacaoRepository
                .findAll()
                .stream()
                .filter(votacao -> ABERTA.equals(votacao.getStatusVotacao()))
                .collect(Collectors.toList())
                .stream()
                .findFirst();

        return validaIsEmpty(optionalVotacao);
    }

    private Votacao validaIsEmpty(Optional<Votacao> optionalVotacao) {
        if (optionalVotacao.isEmpty()) {
            throw new ResourceNotFoundException(messageSourceUtil.message("api.error.votacao.nao.encontrada"));
        }
        return optionalVotacao.get();
    }


}
