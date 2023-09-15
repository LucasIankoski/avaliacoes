package com.vettorello.avaliacoes.repositories;

import com.vettorello.avaliacoes.entities.Componente;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ComponenteRepository extends JpaRepository<Componente, Long> {

    Componente findByDescricao(String descricao);
}
