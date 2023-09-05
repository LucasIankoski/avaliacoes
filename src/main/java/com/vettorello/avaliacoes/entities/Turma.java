package com.vettorello.avaliacoes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turma {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int codigo;
}
