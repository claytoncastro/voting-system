package br.com.project.msvotacao.service.impl.definition;

import br.com.project.msvotacao.service.PautaVotacaoService;
import br.com.project.msvotacao.service.VotacaoCacheService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotacaoDefinitionBean implements Runnable {

    private final VotacaoCacheService votacaoCacheService;
    private final PautaVotacaoService pautaVotacaoService;

    @Override
    @SneakyThrows
    public void run() {
        votacaoCacheService.votacaoEncerrada();
        pautaVotacaoService.encerrarPautaAberta();
        log.info("Votação Encerrada");
    }

}