package com.vettorello.avaliacoes.services;

import com.vettorello.avaliacoes.entities.Avaliacao;
import com.vettorello.avaliacoes.entities.Componente;
import com.vettorello.avaliacoes.entities.Turma;
import com.vettorello.avaliacoes.repositories.AvaliacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao){
        return repository.save(avaliacao);
    }

    public List<Avaliacao> filtrarPorTurmaEComponente(Turma turma, Componente componente) {
        return repository.findByTurmaAndComponente(turma, componente);
    }

    public List<Avaliacao> filtrarComponentesComAvaliacaoPorTurma(Turma turma){
        return repository.findByTurma(turma);
    }
}
