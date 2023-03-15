package br.com.project.msvotacao.service.impl.definition;

import br.com.project.msvotacao.service.VotacaoCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotacaoDefinitionBean implements Runnable {

    private final VotacaoCacheService votacaoCacheService;

    @Override
    public void run() {
        votacaoCacheService.votacaoEncerrada();
        log.info("Votação Encerrada");
    }

}