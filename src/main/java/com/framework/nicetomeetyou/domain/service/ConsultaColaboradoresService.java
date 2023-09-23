package com.framework.nicetomeetyou.domain.service;

import com.framework.nicetomeetyou.api.model.QuantidadeColaboradoresClienteDTO;
import com.framework.nicetomeetyou.domain.model.Colaborador;
import com.framework.nicetomeetyou.domain.repository.ColaboradorRepository;
import com.framework.nicetomeetyou.domain.repository.SquadRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ConsultaColaboradoresService {

    private final ColaboradorRepository colaboradorRepository;
    private final ConsultaClienteService consultaClienteService;
    private final SquadRepository squadRepository;

    @Transactional
    public List<Colaborador> colaboradoresByClienteId(Long cliente_id) {
        return colaboradorRepository.findColaboradoresBySquadsClienteId(cliente_id);
    }

    @Transactional
    public List<QuantidadeColaboradoresClienteDTO> getQuantidadeColaboradoresPorCliente() {
        List<Object[]> resultados = colaboradorRepository.countColaboradoresPorCliente();

        List<QuantidadeColaboradoresClienteDTO> listaQuantidadeClientes = new ArrayList<>();
        for (Object[] resultado : resultados) {
            String clienteId = (String) resultado[0];
            long clienteIdLong = Long.parseLong(clienteId);
            int quantidadeColaboradores = ((Number) resultado[1]).intValue();

            String nomeCliente = consultaClienteService.getNomeCliente(clienteIdLong);

            QuantidadeColaboradoresClienteDTO clienteQuantidade = new QuantidadeColaboradoresClienteDTO(nomeCliente, quantidadeColaboradores);
            listaQuantidadeClientes.add(clienteQuantidade);
        }

        return listaQuantidadeClientes;
    }

}
