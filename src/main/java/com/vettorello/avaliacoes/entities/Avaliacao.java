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
    private Long id;
    private Componente componente;
    private String hyperlink;
    private Turma turma;

}
