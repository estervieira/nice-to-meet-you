package com.framework.nicetomeetyou.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ColaboradorInput {


    @Valid
    private CargoIdInput cargo;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataContratacao;

    @NotBlank
    private String tecnologias;

    @NotBlank
    private String hobbies;

    @NotBlank
    private String comidaFavorita;

    @NotBlank
    private String generoMusical;

    @NotBlank
    private String motivacaoDiaria;

    @NotBlank
    private String nomeCliente;

    @Valid
    private List<SquadIdInput> squads;
}
