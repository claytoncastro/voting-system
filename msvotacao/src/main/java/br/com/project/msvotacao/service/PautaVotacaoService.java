package br.com.project.msvotacao.service;

import br.com.project.msvotacao.dto.service.PautaVotacao;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;

public interface PautaVotacaoService {

    PautaVotacao obterPautaVotacao() throws ErroComunicacaoMicroservicesException;
    void encerrarPautaAberta() throws ErroComunicacaoMicroservicesException;

}
