package com.vettorello.avaliacoes.dtos;


import com.vettorello.avaliacoes.entities.Componente;

public record AvaliacaoDTO(Componente componente,
                           String hyperlink,
                           String turma) {
}
