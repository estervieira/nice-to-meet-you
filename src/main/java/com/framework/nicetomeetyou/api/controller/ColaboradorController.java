package com.framework.nicetomeetyou.api.controller;

import com.framework.nicetomeetyou.api.assembler.ColaboradorAssembler;
import com.framework.nicetomeetyou.api.model.ColaboradorDTO;
import com.framework.nicetomeetyou.api.model.QuantidadeColaboradoresClienteDTO;
import com.framework.nicetomeetyou.api.model.input.ColaboradorInput;
import com.framework.nicetomeetyou.domain.model.Colaborador;
import com.framework.nicetomeetyou.domain.repository.ColaboradorRepository;
import com.framework.nicetomeetyou.domain.service.ConsultaColaboradoresService;
import com.framework.nicetomeetyou.domain.service.RegistroColaboradorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    private final ColaboradorRepository colaboradorRepository;
    private final RegistroColaboradorService registroColaboradorService;
    private final ConsultaColaboradoresService consultaColaboradoresService;
    private final ColaboradorAssembler colaboradorAssembler;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ColaboradorDTO> listar() {
        return colaboradorAssembler.toCollectionDTO(colaboradorRepository.findAll());
    }

    @GetMapping("/{colaboradorId}")
    public ResponseEntity<ColaboradorDTO> buscar(@PathVariable Long colaboradorId) {
        return colaboradorRepository.findById(colaboradorId)
                .map(colaboradorAssembler::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ColaboradorDTO adicionar(@Valid @RequestBody ColaboradorInput colaboradorInput) {

        Colaborador novoColaborador = colaboradorAssembler.toEntity(colaboradorInput);
        Colaborador colaboradorCadastrado = registroColaboradorService.salvar(novoColaborador);

        return colaboradorAssembler.toDTO(colaboradorCadastrado);
    }

    @PutMapping("/{colaboradorId}")
    public ResponseEntity<ColaboradorDTO> atualizar(@PathVariable Long colaboradorId,
                                                 @Valid @RequestBody ColaboradorDTO colaboradorDTO) {
        if(!colaboradorRepository.existsById(colaboradorId)) {
            return ResponseEntity.notFound().build();
        }

        colaboradorDTO.setId(colaboradorId);
        Colaborador colaboradorAtualizado = registroColaboradorService.salvar(modelMapper.map(colaboradorDTO, Colaborador.class));

        return ResponseEntity.ok(modelMapper.map(colaboradorAtualizado, ColaboradorDTO.class));
    }

    @DeleteMapping("/{colaboradorId}")
    public ResponseEntity<Void> remover(@PathVariable Long colaboradorId) {
        if(!colaboradorRepository.existsById(colaboradorId)) {
            return ResponseEntity.notFound().build();
        }

        registroColaboradorService.excluir(colaboradorId);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/quantColaboradores")
    public List<QuantidadeColaboradoresClienteDTO> quantidadeColaboradoresPorCliente() {
        return consultaColaboradoresService.getQuantidadeColaboradoresPorCliente();
    }

    @GetMapping(value = "/cliente/{clienteId}")
    public ResponseEntity<List<ColaboradorDTO>> colaboradoresByClienteId(@PathVariable Long clienteId) {
        List<Colaborador> colaboradores = consultaColaboradoresService.colaboradoresByClienteId(clienteId);

        List<ColaboradorDTO> colaboradoresDTO = colaboradores.stream()
                .map(colaboradorAssembler::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(colaboradoresDTO);
    }


}
