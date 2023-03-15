package br.com.project.msvotacao.service;

import br.com.project.msvotacao.model.Votacao;

public interface VotacaoDadosCacheService {

    Votacao obterVotacaoStatusAberta();
    void encerrarVotacao();

}
