package br.com.project.msvotacao.service.amqp;

import br.com.project.msvotacao.dto.amqp.VotacaoPautaRequestPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotacaoPautaPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueVotacaoPauta;

    public void votacaoPauta(VotacaoPautaRequestPublisher publisher) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(queueVotacaoPauta.getName(), convertIntoJson(publisher));
    }

    private String convertIntoJson(VotacaoPautaRequestPublisher dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);
    }

}
