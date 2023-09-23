package com.framework.nicetomeetyou.domain.service;


import com.framework.nicetomeetyou.domain.model.Cargo;
import com.framework.nicetomeetyou.domain.repository.CargoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistroCargoService {

    private final CargoRepository cargoRepository;

    @Transactional
    public Cargo salvar(Cargo cargo){
        return cargoRepository.save(cargo);
    }

    @Transactional
    public void excluir(Long cargoId) {
        cargoRepository.deleteById(cargoId);
    }

}
