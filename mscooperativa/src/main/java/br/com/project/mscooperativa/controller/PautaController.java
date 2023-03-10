package br.com.project.mscooperativa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    @GetMapping
    public String status() {
        return "OK";
    }

}
