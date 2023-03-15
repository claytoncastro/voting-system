package br.com.project.msvotacao.service.impl;

import br.com.project.msvotacao.model.Votacao;
import br.com.project.msvotacao.service.VotacaoCacheService;
import br.com.project.msvotacao.service.VotacaoDadosCacheService;
import br.com.project.msvotacao.util.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotacaoCacheServiceImpl implements VotacaoCacheService {

    private final VotacaoDadosCacheService votacaoDadosCacheService;
    private final MessageSourceUtil messageSourceUtil;

    @Override
    @Cacheable("cache-votacao")
    public Votacao cacheVotacaoAberta() {
        return votacaoDadosCacheService.obterVotacaoStatusAberta();
    }

    @Override
    @CacheEvict("cache-votacao")
    public void votacaoEncerrada() {
        votacaoDadosCacheService.encerrarVotacao();
    }

}
