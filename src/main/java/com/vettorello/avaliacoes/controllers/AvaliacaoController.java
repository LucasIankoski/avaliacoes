package com.vettorello.avaliacoes.controllers;

import com.vettorello.avaliacoes.dtos.AvaliacaoDTO;
import com.vettorello.avaliacoes.entities.Avaliacao;
import com.vettorello.avaliacoes.entities.Componente;
import com.vettorello.avaliacoes.entities.Turma;
import com.vettorello.avaliacoes.repositories.TurmaRepository;
import com.vettorello.avaliacoes.services.AvaliacaoService;
import com.vettorello.avaliacoes.services.ComponenteService;
import com.vettorello.avaliacoes.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private ComponenteService componenteService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody AvaliacaoDTO dto){
        /*
        if(!turmaRepository.existePorCodigo(dto.turma())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A turma não existe");
        }*/

        Avaliacao avaliacao = new Avaliacao();
        Componente componente = componenteService.filtrarPorDescricao(dto.componente().toUpperCase(Locale.ROOT));
        avaliacao.setComponente(componente);
        avaliacao.setHyperlink(dto.hyperlink());

        Turma turma = turmaService.filtrarTurmaPorCodigo(dto.turma());
        avaliacao.setTurma(turma);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(avaliacao));

    }
/*
    @GetMapping
    public ResponseEntity<List<Avaliacao>> filtrarAvaliacao(
                    @RequestParam("turma") String turma,
                    @RequestParam(value = "componente", required = false) String componente){

        List<Avaliacao> avaliacoes = service.filtrarPorTurmaEComponente(turma, componente);

        if(avaliacoes.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avaliacoes);
    }*/
}
