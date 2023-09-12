package com.vettorello.avaliacoes.dtos;

import com.vettorello.avaliacoes.entities.Turma;
import com.vettorello.avaliacoes.enums.Componente;

public record AvaliacaoDTO(Componente componente,
                           String hyperlink,
                           String turma) {
}
