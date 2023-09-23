package com.framework.nicetomeetyou.api.model.input;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CargoInput {

    @NotBlank
    private String nome;
}
