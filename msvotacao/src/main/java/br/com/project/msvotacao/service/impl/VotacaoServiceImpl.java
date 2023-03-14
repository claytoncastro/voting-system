package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.dto.service.PautaVotacao;
import br.com.project.msvotacao.dto.service.response.post.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import br.com.project.msvotacao.service.PautaVotacaoService;
import br.com.project.msvotacao.service.VotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private final PautaVotacaoService pautaVotacaoService;

    @Override
    public VotacaoPostResponse abrirVotacao(int tempoParavotacao) throws ErroComunicacaoMicroservicesException {
        PautaVotacao pautaVotacao = obterPautaVotacao();

        // TODO: Abrir votação

        return VotacaoPostResponse.from(pautaVotacao);
    }

    private PautaVotacao obterPautaVotacao() throws ErroComunicacaoMicroservicesException {
        return pautaVotacaoService.obterPautaVotacao();
    }

}
