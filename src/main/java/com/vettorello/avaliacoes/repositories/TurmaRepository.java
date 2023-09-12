package com.vettorello.avaliacoes.repositories;

import com.vettorello.avaliacoes.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

   Turma findByCodigo(String codigo);

   //boolean existePorCodigo(String codigo);
}
