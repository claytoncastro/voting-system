package br.com.project.msvotacao.controller;

import br.com.project.msvotacao.dto.service.post.request.VotoPostRequest;
import br.com.project.msvotacao.dto.service.post.response.VotacaoPostResponse;
import br.com.project.msvotacao.exception.ErroComunicacaoMicroservicesException;
import br.com.project.msvotacao.service.VotacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping("/votar")
    public ResponseEntity<Void> votar(@RequestBody @Valid VotoPostRequest request) {
        try {
            votacaoService.votar(request);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

}
