package com.checkpoint.clinica.controller;

import com.checkpoint.clinica.controller.dto.ConsultaResponse;
import com.checkpoint.clinica.controller.dto.DentistaResponse;
import com.checkpoint.clinica.exeption.ResourceNotFoundException;
import com.checkpoint.clinica.model.Consulta;
import com.checkpoint.clinica.model.Usuario;
import com.checkpoint.clinica.service.impl.ConsultaService;
import com.checkpoint.clinica.service.impl.DentistaService;
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
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
      private ConsultaService consultaService;
    @Autowired
      private DentistaService dentistaService;
    @Autowired
      private PacienteService pacienteService;


    @Operation(summary = "Procurar pelo id da consulta")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscarId (@PathVariable Integer id){
        ResponseEntity<ConsultaResponse> response;
        if (Objects.nonNull(consultaService.buscarPorId(id))){
            response = new ResponseEntity(consultaService.buscarPorId(id),HttpStatus.OK);
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
    @Operation(summary = "Cadastrar uma nova consulta")
    @PostMapping
    public ResponseEntity<ConsultaResponse> cadastro(@RequestBody Consulta consulta) throws ResourceNotFoundException {
         if(Objects.nonNull(consulta.getPaciente()) && Objects.nonNull(consulta.getDentista())
         ){
             return ResponseEntity.ok(consultaService.salvar(consulta));
         }else {
             throw new ResourceNotFoundException("Erro ao se cadastrar");
         }
     }
    @Operation(summary = "Procurar pelo nome da paciente pra consulta")
    @GetMapping("/nome/{nome}")
    public ResponseEntity <ConsultaResponse>buscarPorNome(@PathVariable String nome ) throws ResourceNotFoundException {
        if (Objects.nonNull(consultaService.buscarPorNome(nome))){
            return ResponseEntity.ok(consultaService.buscarPorNome(nome));
        }else {
            throw new ResourceNotFoundException("Erro ao buscar Por nome Consulta");
        }
    }
    @Operation(summary = "Listar todas as consultas")
     @GetMapping
    public ResponseEntity<List<ConsultaResponse>>buscarTodos() throws ResourceNotFoundException {
         List<ConsultaResponse> dentistaResponses = consultaService.buscarTodos();
         if (!dentistaResponses.isEmpty()) {
             return ResponseEntity.ok(dentistaResponses);
         } else {
             throw new ResourceNotFoundException("Erro ao gerar a lista");
         }
     }
    @Operation(summary = "Atualizar dados da consulta")
     @PutMapping
      public  ResponseEntity<ConsultaResponse> atualizar(@RequestBody Consulta consulta) throws ResourceNotFoundException {
        if(Objects.nonNull(consultaService.buscarPorId(consulta.getId()))){
             return ResponseEntity.ok(consultaService.atualizar(consulta));
         }else {
             throw new ResourceNotFoundException("Erro ao gerar a lista");
         }
    }
    @Operation(summary = "Deletar  pelo id a consulta")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) throws ResourceNotFoundException {
         if (Objects.nonNull(consultaService.buscarPorId(id))){
             consultaService.excluir(id);
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Consulta apagada");
         }else {
             throw new ResourceNotFoundException("Erro ao gerar a lista");
         }
    }


}
