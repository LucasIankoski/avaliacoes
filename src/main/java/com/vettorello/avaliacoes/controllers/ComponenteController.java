package com.vettorello.avaliacoes.controllers;

import com.vettorello.avaliacoes.dtos.ComponenteDTO;
import com.vettorello.avaliacoes.entities.Componente;
import com.vettorello.avaliacoes.services.ComponenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/componente")
public class ComponenteController {

    @Autowired
    private ComponenteService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody ComponenteDTO dto){
        Componente componente = Componente.builder().descricao(dto.descricao()).build();
        try{
            Componente novoComponente = service.salvar(componente);
            return new ResponseEntity(novoComponente, HttpStatus.CREATED);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

}
