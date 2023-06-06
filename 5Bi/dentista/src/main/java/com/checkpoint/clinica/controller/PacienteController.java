package com.checkpoint.clinica.controller;

import com.checkpoint.clinica.controller.dto.PacienteResponse;
import com.checkpoint.clinica.controller.dto.UsuarioResponse;
import com.checkpoint.clinica.exeption.ResourceNotFoundException;
import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.model.Paciente;
import com.checkpoint.clinica.model.Usuario;
import com.checkpoint.clinica.service.impl.EnderecoService;
import com.checkpoint.clinica.service.impl.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EnderecoService enderecoService;
    @Operation(summary = "Procurar dados pelo id do paciente")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarId (@PathVariable Integer id)throws ResourceNotFoundException{
        if (Objects.nonNull(pacienteService.buscarPorId(id))){
            return new ResponseEntity(pacienteService.buscarPorId(id),HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Não ha Registros de Dentistas");
        }
    }
    @Operation(summary = "Listar Todos os pacientes")
    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodas() throws ResourceNotFoundException {
        List<PacienteResponse> pacienteResponses = pacienteService.buscarTodos();
        if (!pacienteResponses.isEmpty()) {
            return ResponseEntity.ok(pacienteResponses);
        } else {
            throw new ResourceNotFoundException("Erro ao gerar a lista");
        }
    }
    @Operation(summary = "Procurar pelo nome do paciente")
    @GetMapping("/nome/{nome}")
    public ResponseEntity <PacienteResponse>buscarPorNome(@PathVariable String nome )throws ResourceNotFoundException {
        if (Objects.nonNull(pacienteService.buscarPorNome(nome))){
            return ResponseEntity.ok(pacienteService.buscarPorNome(nome));
        }else {
            throw new ResourceNotFoundException("Nao existe este nome de Paciente");
        }

    }
    @Operation(summary = "Deletar paciente")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) throws ResourceNotFoundException {
        if (Objects.nonNull(pacienteService.buscarPorId(id))){
            pacienteService.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("paciente apagado");
        }else {
            throw new ResourceNotFoundException("Paciente não encontrado");
        }

    }
    @Operation(summary = "Cadastra um novo paciente")
    @PostMapping
    public ResponseEntity<PacienteResponse> cadastrar(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        if(validaEndereco(paciente)){
            return ResponseEntity.ok(pacienteService.salvar(paciente));
        }else {
            throw new ResourceNotFoundException("Erro ao se cadastrar");
        }
    }
    @Operation(summary = "Atualizar o dados do paciente")
    @PutMapping
    public ResponseEntity<PacienteResponse> atualizar(@RequestBody Paciente paciente) throws ResourceNotFoundException{
        if(Objects.nonNull(pacienteService.buscarPorId(paciente.getId()))){
            return ResponseEntity.ok(pacienteService.atualizar(paciente));
        }else {
            throw new ResourceNotFoundException("Erro ao Atualizar");
        }
    }

    private static boolean validaEndereco(Paciente paciente){
     return Objects.nonNull(paciente.getEndereco()) &&
             Objects.nonNull(paciente.getEndereco().getRua()) &&
             !paciente.getEndereco().getRua().isBlank() &&
             !paciente.getEndereco().getRua().isEmpty() &&
             Objects.nonNull(paciente.getEndereco().getCidade()) &&
             !paciente.getEndereco().getCidade().isEmpty() &&
             !paciente.getEndereco().getCidade().isBlank() &&
             Objects.nonNull(paciente.getEndereco().getEstado()) &&
             !paciente.getEndereco().getEstado().isEmpty() &&
             !paciente.getEndereco().getEstado().isBlank() &&
             Objects.nonNull(paciente.getEndereco().getNumero());
    }


}
