package com.vettorello.avaliacoes.controllers;

import com.vettorello.avaliacoes.dtos.AvaliacaoDTO;
import com.vettorello.avaliacoes.dtos.ComponenteDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

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

        if(componente == null){
            return ResponseEntity.badRequest().body("Componente não encontrado no cadastro.");
        }

        avaliacao.setComponente(componente);
        avaliacao.setHyperlink(dto.hyperlink());
        avaliacao.setTitulo(dto.titulo());

        Turma turma = turmaService.filtrarTurmaPorCodigo(dto.turma());
        avaliacao.setTurma(turma);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(avaliacao));

    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> filtrarAvaliacao(
                    @RequestParam("turma") String turma,
                    @RequestParam(value = "componente", required = true) String componente){

        Turma objTurma = turmaService.filtrarTurmaPorCodigo(turma);
        Componente objCompomente = componenteService.filtrarPorDescricao(componente.toUpperCase(Locale.ROOT));


        List<Avaliacao> avaliacoes = service.filtrarPorTurmaEComponente(objTurma, objCompomente);

        if(avaliacoes.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping(value = "/{turma}")
    public ResponseEntity<List<ComponenteDTO>> filtrarComponentesComAvaliacaoPorTurma(@PathVariable("turma") String turma){
        Turma objTurma = turmaService.filtrarTurmaPorCodigo(turma);
        List<Avaliacao> avaliacoes = service.filtrarComponentesComAvaliacaoPorTurma(objTurma);

        if(avaliacoes.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<ComponenteDTO> componentesDTO = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            Componente componente = avaliacao.getComponente();
            ComponenteDTO componenteDTO = new ComponenteDTO(componente.getDescricao());
            componentesDTO.add(componenteDTO);
        }

        return ResponseEntity.ok(componentesDTO);
    }
}
