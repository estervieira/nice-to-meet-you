package com.framework.nicetomeetyou.api.assembler;


import com.framework.nicetomeetyou.api.model.ClienteDTO;
import com.framework.nicetomeetyou.domain.model.ClienteAPI;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ClienteAssembler {

    private final ModelMapper modelMapper;

    public ClienteDTO toDTO(ClienteAPI clienteAPI) {
        return modelMapper.map(clienteAPI, ClienteDTO.class);
    }

    public List<ClienteDTO> toCollectionDTO(List<ClienteAPI> clientes) {
        return clientes.stream()
                .map(this::toDTO)
                .toList();
    }
}
