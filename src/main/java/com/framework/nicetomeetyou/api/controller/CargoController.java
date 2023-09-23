package com.framework.nicetomeetyou.api.controller;

import com.framework.nicetomeetyou.api.assembler.CargoAssembler;
import com.framework.nicetomeetyou.api.model.CargoDTO;
import com.framework.nicetomeetyou.api.model.input.CargoInput;
import com.framework.nicetomeetyou.domain.model.Cargo;
import com.framework.nicetomeetyou.domain.repository.CargoRepository;
import com.framework.nicetomeetyou.domain.service.RegistroCargoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cargos")
public class CargoController {

    private final CargoRepository cargoRepository;
    private final RegistroCargoService registroCargoService;
    private final CargoAssembler cargoAssembler;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CargoDTO> listar() {
        return cargoAssembler.toCollectionDTO(cargoRepository.findAll());
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<CargoDTO> buscar(@PathVariable Long cargoId) {
        return cargoRepository.findById(cargoId)
                .map(cargoAssembler::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CargoDTO adicionar(@Valid @RequestBody CargoInput cargoInput) {
        Cargo novoCargo = cargoAssembler.toEntity(cargoInput);
        Cargo cargoCadastrado = registroCargoService.salvar(novoCargo);

        return cargoAssembler.toDTO(cargoCadastrado);
    }

    @PutMapping("/{cargoId}")
    public ResponseEntity<CargoDTO> atualizar(@PathVariable Long cargoId,
                                                 @Valid @RequestBody CargoDTO cargoDTO) {
        if(!cargoRepository.existsById(cargoId)) {
            return ResponseEntity.notFound().build();
        }

        cargoDTO.setId(cargoId);
        Cargo cargoAtualizado = registroCargoService.salvar(modelMapper.map(cargoDTO, Cargo.class));

        return ResponseEntity.ok(modelMapper.map(cargoAtualizado, CargoDTO.class));
    }

    @DeleteMapping("/{cargoId}")
    public ResponseEntity<Void> remover(@PathVariable Long cargoId) {
        if(!cargoRepository.existsById(cargoId)) {
            return ResponseEntity.notFound().build();
        }

        registroCargoService.excluir(cargoId);
        return ResponseEntity.notFound().build();
    }

}
