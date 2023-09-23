package com.framework.nicetomeetyou.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.framework.nicetomeetyou.domain.model.ClienteAPI;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ConsultaClienteService {

    private List<ClienteAPI> clientes;

    public ConsultaClienteService() {
        clientes = loadClientesFromJson();
    }

    @Transactional
    public List<ClienteAPI> getAllClientes() {
        return clientes;
    }

    @Transactional
    public ClienteAPI getClienteById(Long id) {
        Optional<ClienteAPI> clienteOptional = clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst();

        return clienteOptional.orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

    @Transactional
    public String getNomeCliente(Long clienteId) {
        String clienteIdString = String.valueOf(clienteId);
        for (ClienteAPI cliente : clientes) {
            if (String.valueOf(cliente.getId()).equals(clienteIdString)) {
                return cliente.getName();
            }
        }
        return null;
    }

    private List<ClienteAPI> loadClientesFromJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/clientes.json");
            List<ClienteAPI> clientes = objectMapper.readValue(inputStream, new TypeReference<List<ClienteAPI>>() {});
            return clientes;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar os dados de clientes do arquivo JSON.", e);
        }
    }

}
