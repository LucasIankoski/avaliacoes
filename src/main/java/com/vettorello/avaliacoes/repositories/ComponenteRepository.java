package com.vettorello.avaliacoes.repositories;

import com.vettorello.avaliacoes.entities.Avaliacao;
import com.vettorello.avaliacoes.entities.Componente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponenteRepository extends JpaRepository<Componente, Long> {

}
