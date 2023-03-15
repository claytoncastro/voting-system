package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.dto.service.PautaVotacao;
import br.com.project.msvotacao.dto.service.response.post.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import br.com.project.msvotacao.service.PautaVotacaoService;
import br.com.project.msvotacao.service.TaskSchedulingService;
import br.com.project.msvotacao.service.VotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private final PautaVotacaoService pautaVotacaoService;
    private final TaskSchedulingService taskSchedulingService;

    @Override
    public VotacaoPostResponse abrirVotacao(long tempoParavotacao) throws ErroComunicacaoMicroservicesException {
        PautaVotacao pautaVotacao = obterPautaVotacao();

        /* TODO: Abrir votação
        *   Salvar Votacao
        *       - id
        *       - tema
        *       - comeco
        *       - final
        *       - status (VOTACAO_ABERTA)
        * */

        taskSchedulingService.scheduleATask(tempoParavotacao);
        return VotacaoPostResponse.from(pautaVotacao);
    }

    private PautaVotacao obterPautaVotacao() throws ErroComunicacaoMicroservicesException {
        return pautaVotacaoService.obterPautaVotacao();
    }

}
