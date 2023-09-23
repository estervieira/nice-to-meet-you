package com.framework.nicetomeetyou.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long clienteId;
    private String nome;

    @ManyToMany(mappedBy = "squads", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Colaborador> colaboradores;
}
