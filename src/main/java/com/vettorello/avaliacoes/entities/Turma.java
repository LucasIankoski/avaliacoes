package com.vettorello.avaliacoes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@Table(name = "turmas", schema = "vettorello")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo", length = 255)
    private String codigo;

}
