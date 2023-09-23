package com.framework.nicetomeetyou.api.controller;


import com.framework.nicetomeetyou.api.assembler.SquadAssembler;
import com.framework.nicetomeetyou.api.model.SquadDTO;
import com.framework.nicetomeetyou.api.model.input.SquadInput;
import com.framework.nicetomeetyou.domain.model.Squad;
import com.framework.nicetomeetyou.domain.repository.SquadRepository;
import com.framework.nicetomeetyou.domain.service.RegistroSquadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/squads")
public class SquadController {

    private final SquadRepository squadRepository;
    private final RegistroSquadService registroSquadService;
    private final SquadAssembler squadAssembler;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<SquadDTO> listar() {
        return squadAssembler.toCollectionDTO(squadRepository.findAll());
    }

    @GetMapping("/{squadId}")
    public ResponseEntity<SquadDTO> buscar(@PathVariable Long squadId) {
        return squadRepository.findById(squadId)
                .map(squadAssembler::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SquadDTO adicionar(@Valid @RequestBody SquadInput squadInput) {
        Squad novaSquad = squadAssembler.toEntity(squadInput);
        Squad squadCadastrada = registroSquadService.salvar(novaSquad);

        return squadAssembler.toDTO(squadCadastrada);
    }

    @PutMapping("/{squadId}")
    public ResponseEntity<SquadDTO> atualizar(@PathVariable Long squadId,
                                              @Valid @RequestBody SquadDTO squadDTO) {
        if(!squadRepository.existsById(squadId)) {
            return ResponseEntity.notFound().build();
        }

        squadDTO.setId(squadId);
        Squad squadAtualizada = registroSquadService.salvar(modelMapper.map(squadDTO, Squad.class));

        return ResponseEntity.ok(modelMapper.map(squadAtualizada, SquadDTO.class));
    }

    @DeleteMapping("/{squadId}")
    public ResponseEntity<Void> remover(@PathVariable Long squadId) {
        if(!squadRepository.existsById(squadId)) {
            return ResponseEntity.notFound().build();
        }

        registroSquadService.excluir(squadId);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/cliente/{clienteId}")
    public ResponseEntity<List<Squad>> squadByClientId(@PathVariable("clienteId") Long clienteId) {
        List<Squad> squads = registroSquadService.findSquadsByClienteId(clienteId);
        return ResponseEntity.ok(squads);
    }
}
