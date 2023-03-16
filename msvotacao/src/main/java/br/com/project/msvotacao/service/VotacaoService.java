package br.com.project.msvotacao.service;

import br.com.project.msvotacao.dto.service.post.request.VotoPostRequest;
import br.com.project.msvotacao.dto.service.post.response.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface VotacaoService {

    VotacaoPostResponse abrirVotacao(long tempoParavotacao) throws ErroComunicacaoMicroservicesException;
    void votar(VotoPostRequest request) throws JsonProcessingException;

}
