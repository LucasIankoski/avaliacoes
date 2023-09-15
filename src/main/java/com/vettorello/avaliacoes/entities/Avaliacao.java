package com.vettorello.avaliacoes.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "avaliacao", schema = "vettorello")
public class Avaliacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "componente")
    private Componente componente;

    @Column(name = "hyperlink")
    private String hyperlink;

    @ManyToOne
    @JoinColumn(name = "turma")
    private Turma turma;

}
