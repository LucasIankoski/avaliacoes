package com.vettorello.avaliacoes.repositories;

import com.vettorello.avaliacoes.entities.Avaliacao;
import com.vettorello.avaliacoes.entities.Componente;
import com.vettorello.avaliacoes.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByTurmaAndComponente(Turma turma, Componente componente);
}
