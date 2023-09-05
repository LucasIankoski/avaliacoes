package com.vettorello.avaliacoes.repositories;

import com.vettorello.avaliacoes.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
