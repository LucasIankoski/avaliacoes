package com.vettorello.avaliacoes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios", schema = "vettorello")
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "nome", length = 255)
    private String nome;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "cargo")
    private String cargo;

}
