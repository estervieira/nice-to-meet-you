package com.framework.nicetomeetyou.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Colaborador {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cargo cargo;

    private String nome;
    private LocalDate dataContratacao;
    private String tecnologias;
    private String hobbies;
    private String comidaFavorita;
    private String generoMusical;
    private String motivacaoDiaria;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "colaborador_squad",
                joinColumns = @JoinColumn(name = "colaborador_id"),
                inverseJoinColumns = @JoinColumn(name = "squad_id"))
    private List<Squad> squads;

}
