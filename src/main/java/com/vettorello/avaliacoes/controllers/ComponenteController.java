package com.vettorello.avaliacoes.controllers;

import com.vettorello.avaliacoes.dtos.ComponenteDTO;
import com.vettorello.avaliacoes.entities.Componente;
import com.vettorello.avaliacoes.services.ComponenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/componente")
public class ComponenteController {

    @Autowired
    private ComponenteService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody ComponenteDTO dto){
        Componente componente = Componente.builder().descricao(dto.descricao().toUpperCase(Locale.ROOT)).build();
        try{
            Componente novoComponente = service.salvar(componente);
            return new ResponseEntity(novoComponente, HttpStatus.CREATED);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @GetMapping
    public ResponseEntity buscarComponente(){

        List<Componente> componentes = service.buscarTodosComponentes();
        return ResponseEntity.ok().body(componentes);
    }

}
