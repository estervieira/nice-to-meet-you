package com.framework.nicetomeetyou.domain.service;

import com.framework.nicetomeetyou.domain.model.Squad;
import com.framework.nicetomeetyou.domain.repository.SquadRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RegistroSquadService {

    private final SquadRepository squadRepository;

    @Transactional
    public Squad salvar(Squad squad){
        return squadRepository.save(squad);
    }

    @Transactional
    public void excluir(Long squadId) {
        squadRepository.deleteById(squadId);
    }

    @Transactional
    public List<Squad> findSquadsByClienteId(Long clienteId) {
        return squadRepository.findByClienteId(clienteId);
    }


}
