package br.com.project.mscooperativa.controller;

import br.com.project.mscooperativa.dto.request.PautaRequest;
import br.com.project.mscooperativa.dto.response.get.PautaGetResponse;
import br.com.project.mscooperativa.dto.response.post.PautaPostResponse;
import br.com.project.mscooperativa.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pautas")
public class PautaController {

    private final PautaService pautaService;

    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<PautaPostResponse> salvarPauta(@RequestBody @Valid PautaRequest request) {
        return new ResponseEntity<>(pautaService.salvarPauta(request), CREATED);
    }

    @GetMapping("/pauta-aberta")
    public ResponseEntity<PautaGetResponse> obterPautasAbertas() {
        return new ResponseEntity<>(pautaService.obterPautasAbertas(), OK);
    }


}
