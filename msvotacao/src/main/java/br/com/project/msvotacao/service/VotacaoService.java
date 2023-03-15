package br.com.project.msvotacao.service;

import br.com.project.msvotacao.dto.service.response.post.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;

public interface VotacaoService {

    VotacaoPostResponse abrirVotacao(long tempoParavotacao) throws ErroComunicacaoMicroservicesException;
    void votar();

}
