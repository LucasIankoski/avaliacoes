package com.vettorello.avaliacoes.services;

import com.vettorello.avaliacoes.entities.Avaliacao;
import com.vettorello.avaliacoes.repositories.AvaliacaoRepository;
import com.vettorello.avaliacoes.repositories.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao){
        return repository.save(avaliacao);
    }
}
