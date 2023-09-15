package com.vettorello.avaliacoes.services;

import com.vettorello.avaliacoes.entities.Componente;
import com.vettorello.avaliacoes.repositories.ComponenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponenteService {

    @Autowired
    private ComponenteRepository repository;

    @Transactional
    public Componente salvar(Componente componente){
        return repository.save(componente);
    }

    public Componente filtrarPorDescricao(String descricao){
        return repository.findByDescricao(descricao);
    }
}
