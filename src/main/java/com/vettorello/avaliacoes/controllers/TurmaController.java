package com.vettorello.avaliacoes.controllers;

import com.vettorello.avaliacoes.dtos.TurmaDTO;
import com.vettorello.avaliacoes.entities.Turma;
import com.vettorello.avaliacoes.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/turma")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody TurmaDTO dto){
        Turma turma = Turma.builder().codigo(dto.codigo()).build();
        try{
            Turma novaTurma = service.cadastrar(turma);
            return new ResponseEntity(novaTurma, HttpStatus.CREATED);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity buscarTodasTurmas(){
        List<Turma> turmas = service.buscar();
        return ResponseEntity.ok(turmas);
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity filtrarTurma(@PathVariable("codigo") String codigo){

        Turma turma = service.filtrarTurmaPorCodigo(codigo);

        return ResponseEntity.ok().body(turma);
    }

}
