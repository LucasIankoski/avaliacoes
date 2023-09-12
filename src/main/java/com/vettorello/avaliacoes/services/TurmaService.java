package com.vettorello.avaliacoes.services;


import com.vettorello.avaliacoes.entities.Turma;
import com.vettorello.avaliacoes.repositories.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;

    @Transactional
    public Turma cadastrar(Turma turma){
        return repository.save(turma);
    }

    public List<Turma> buscar(){
        return repository.findAll();
    }

    public Turma filtrarTurmaPorCodigo(String codigo){
        return repository.findByCodigo(codigo);
    }
}
