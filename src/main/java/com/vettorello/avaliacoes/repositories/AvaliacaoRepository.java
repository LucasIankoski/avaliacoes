package com.vettorello.avaliacoes.repositories;

import com.vettorello.avaliacoes.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    //List<Avaliacao> findByTurmaCodigoAndComponente(String turma, String componente);
}
