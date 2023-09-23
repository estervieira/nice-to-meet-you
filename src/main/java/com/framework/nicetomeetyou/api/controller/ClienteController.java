package com.framework.nicetomeetyou.api.controller;

import com.framework.nicetomeetyou.api.assembler.ClienteAssembler;
import com.framework.nicetomeetyou.api.model.ClienteDTO;
import com.framework.nicetomeetyou.domain.model.ClienteAPI;
import com.framework.nicetomeetyou.domain.service.ConsultaClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ConsultaClienteService consultaClienteService;
    private final ClienteAssembler clienteAssembler;


    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteAssembler.toCollectionDTO(consultaClienteService.getAllClientes());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Long clienteId) {
        Optional<ClienteAPI> clienteOptional = Optional.ofNullable(consultaClienteService.getClienteById(clienteId));
        if (clienteOptional.isPresent()) {
            ClienteAPI cliente = clienteOptional.get();
            ClienteDTO clienteDTO = clienteAssembler.toDTO(cliente);
            return ResponseEntity.ok(clienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
