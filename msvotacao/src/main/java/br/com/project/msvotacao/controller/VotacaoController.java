package br.com.project.msvotacao.controller;

import br.com.project.msvotacao.dto.service.response.post.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import br.com.project.msvotacao.service.VotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votacao")
public class VotacaoController {

    private final VotacaoService votacaoService;

    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<VotacaoPostResponse> abrirVotacao(
            @RequestParam(name = "tempoParavotacao", defaultValue = "1") long tempoParavotacao) {
        VotacaoPostResponse response;
        try {
            response = votacaoService.abrirVotacao(tempoParavotacao);
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.internalServerError().build();
        }
        return new ResponseEntity<>(response, OK);
    }

}
