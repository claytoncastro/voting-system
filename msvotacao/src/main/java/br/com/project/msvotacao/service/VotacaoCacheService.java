package br.com.project.msvotacao.service;

import br.com.project.msvotacao.model.Votacao;

public interface VotacaoCacheService {

    Votacao cacheVotacaoAberta();
    void votacaoEncerrada();

}