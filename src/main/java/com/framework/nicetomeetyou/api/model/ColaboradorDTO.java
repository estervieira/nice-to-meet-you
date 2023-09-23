package com.framework.nicetomeetyou.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ColaboradorDTO {

    private Long id;
    private CargoDTO cargo;
    private String nome;
    private LocalDate dataContratacao;
    private String tecnologias;
    private String hobbies;
    private String comidaFavorita;
    private String generoMusical;
    private String motivacaoDiaria;
    private String nomeCliente;
    private List<SquadDTO> squads;
}
