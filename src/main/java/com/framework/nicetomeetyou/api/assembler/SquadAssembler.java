package com.framework.nicetomeetyou.api.assembler;


import com.framework.nicetomeetyou.api.model.CargoDTO;
import com.framework.nicetomeetyou.api.model.SquadDTO;
import com.framework.nicetomeetyou.api.model.input.CargoInput;
import com.framework.nicetomeetyou.api.model.input.SquadInput;
import com.framework.nicetomeetyou.domain.model.Cargo;
import com.framework.nicetomeetyou.domain.model.Squad;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class SquadAssembler {

    private final ModelMapper modelMapper;

    public Squad toEntity(SquadInput squadInput){
        return modelMapper.map(squadInput, Squad.class);
    }

    public SquadDTO toDTO(Squad squad) {
        return modelMapper.map(squad, SquadDTO.class);
    }

    public List<SquadDTO> toCollectionDTO(List<Squad> squads) {
        return squads.stream()
                .map(this::toDTO)
                .toList();
    }
}
