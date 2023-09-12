package com.vettorello.avaliacoes.controllers;

import com.vettorello.avaliacoes.dtos.AvaliacaoDTO;
import com.vettorello.avaliacoes.entities.Avaliacao;
import com.vettorello.avaliacoes.entities.Turma;
import com.vettorello.avaliacoes.repositories.TurmaRepository;
import com.vettorello.avaliacoes.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping
    public ResponseEntity salvar(@RequestBody AvaliacaoDTO dto){
        if(!turmaRepository.existePorCodigo(dto.turma())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A turma não existe");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComponente(dto.componente());
        avaliacao.setHyperlink(dto.hyperlink());
        Turma turma = turmaRepository.findByCodigo(dto.turma());
        avaliacao.setTurma(turma);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(avaliacao));

    }
}
