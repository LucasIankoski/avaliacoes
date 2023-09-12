package com.vettorello.avaliacoes.entities;

import com.vettorello.avaliacoes.enums.Componente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "avaliacao")
public class Avaliacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "componente")
    private Componente componente;

    @Column(name = "hyperlink")
    private String hyperlink;

    @ManyToOne
    @JoinColumn(name = "turma")
    private Turma turma;

}
