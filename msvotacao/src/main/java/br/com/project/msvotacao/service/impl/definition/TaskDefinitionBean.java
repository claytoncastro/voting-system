package br.com.project.msvotacao.service.impl.definition;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskDefinitionBean implements Runnable {

    @Override
    public void run() {
        System.out.println("Encerrando Votação");
    }

}