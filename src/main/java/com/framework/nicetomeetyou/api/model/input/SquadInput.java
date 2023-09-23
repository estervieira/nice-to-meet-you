package com.framework.nicetomeetyou.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SquadInput {

    @NotNull
    private Long clienteId;
    @NotBlank
    private String nome;
}
