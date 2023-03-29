package com.checkpoint.clinica.controller;

import com.checkpoint.clinica.model.Paciente;
import com.checkpoint.clinica.service.impl.EnderecoService;
import com.checkpoint.clinica.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarId (@PathVariable Integer id){
        ResponseEntity<Paciente> response;
        if (pacienteService.buscarPorId(id).isPresent()){
            response = new ResponseEntity(pacienteService.buscarPorId(id),HttpStatus.OK);
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodas() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        ResponseEntity<String> response;
        if (pacienteService.buscarPorId(id).isPresent()){
            pacienteService.excluir(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("paciente apagado");
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response;
        if(paciente.getNome() != null){
            response = ResponseEntity.ok(pacienteService.salvar(paciente));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Paciente> atualizar(@RequestBody Paciente paciente){
        ResponseEntity<Paciente> response;
        if(pacienteService.buscarPorId(paciente.getId()).isPresent()){
            response = ResponseEntity.ok(pacienteService.atualizar(paciente));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    //private static boolean validaEndereco(Paciente paciente){
     //return Objects.nonNull(paciente.getEnderecos()) //&&
          //   !paciente.getEnderecos());
    //}


}
