package com.framework.nicetomeetyou.api.assembler;

import com.framework.nicetomeetyou.api.model.ColaboradorDTO;
import com.framework.nicetomeetyou.api.model.input.ColaboradorInput;
import com.framework.nicetomeetyou.domain.model.Colaborador;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ColaboradorAssembler {

    private final ModelMapper modelMapper;

    public Colaborador toEntity(ColaboradorInput colaboradorInput){
        return modelMapper.map(colaboradorInput, Colaborador.class);
    }

    public ColaboradorDTO toDTO(Colaborador colaborador) {
        return modelMapper.map(colaborador, ColaboradorDTO.class);
    }

    public List<ColaboradorDTO> toCollectionDTO(List<Colaborador> colaboradores) {
        return colaboradores.stream()
                .map(this::toDTO)
                .toList();
    }

}
