package br.com.project.msvotacao.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.votacao-pauta}")
    private String votacaoPautaFila;

    @Bean
    public Queue queueVotacaoPauta() {
        return new Queue(votacaoPautaFila, true);
    }

}
