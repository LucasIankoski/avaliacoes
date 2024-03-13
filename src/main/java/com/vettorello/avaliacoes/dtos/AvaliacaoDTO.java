package com.vettorello.avaliacoes.dtos;


import com.vettorello.avaliacoes.entities.Componente;

public record AvaliacaoDTO(String componente,
                           String hyperlink,
                           String turma,
                           String titulo) {
}
