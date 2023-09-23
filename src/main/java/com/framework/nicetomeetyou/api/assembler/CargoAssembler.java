package com.framework.nicetomeetyou.api.assembler;


import com.framework.nicetomeetyou.api.model.CargoDTO;
import com.framework.nicetomeetyou.api.model.input.CargoInput;
import com.framework.nicetomeetyou.domain.model.Cargo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CargoAssembler {

    private final ModelMapper modelMapper;

    public Cargo toEntity(CargoInput cargoInput){
        return modelMapper.map(cargoInput, Cargo.class);
    }

    public CargoDTO toDTO(Cargo cargo) {
        return modelMapper.map(cargo, CargoDTO.class);
    }

    public List<CargoDTO> toCollectionDTO(List<Cargo> cargos) {
        return cargos.stream()
                .map(this::toDTO)
                .toList();
    }
}
