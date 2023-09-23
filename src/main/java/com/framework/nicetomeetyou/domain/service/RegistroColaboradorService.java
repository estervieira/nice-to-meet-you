package com.framework.nicetomeetyou.domain.service;


import com.framework.nicetomeetyou.domain.model.Colaborador;
import com.framework.nicetomeetyou.domain.repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistroColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    @Transactional
    public Colaborador salvar(Colaborador colaborador){
        return colaboradorRepository.save(colaborador);
    }

    @Transactional
    public void excluir(Long colaboradorId) {
        colaboradorRepository.deleteById(colaboradorId);
    }



}
