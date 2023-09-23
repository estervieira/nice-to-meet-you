package com.framework.nicetomeetyou.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoIdInput {

    @NotNull
    private Long id;
}
