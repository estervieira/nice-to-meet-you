package com.framework.nicetomeetyou.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantidadeColaboradoresClienteDTO {

    private String nomeCliente;
    private int quantidadeColaboradores;

    public QuantidadeColaboradoresClienteDTO(String nomeCliente, int quantidadeColaboradores) {
        this.nomeCliente = nomeCliente;
        this.quantidadeColaboradores = quantidadeColaboradores;
    }

}
