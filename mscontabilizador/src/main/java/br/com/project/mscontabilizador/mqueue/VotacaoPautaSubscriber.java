package br.com.project.mscontabilizador.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class VotacaoPautaSubscriber {

    @RabbitListener(queues = "${mq.queues.votacao-pauta}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        System.out.println(payload);
    }

}
