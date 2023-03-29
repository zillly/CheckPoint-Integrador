package com.checkpoint.clinica.controller;

import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.service.impl.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")

public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @GetMapping
    public ResponseEntity<List<Dentista>>listarTodas() {
        return ResponseEntity.ok(dentistaService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        ResponseEntity<String> response;
        if (dentistaService.buscarPorId(id).isPresent()){
            dentistaService.excluir(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("dentista apagado");
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity <Optional<Dentista>>buscarPorNome(@PathVariable String nome ){
        return ResponseEntity.ok(dentistaService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Dentista> cadastrar(@RequestBody Dentista dentista) {
        ResponseEntity<Dentista> response;
        if(validarDentista(dentista)){
            response = ResponseEntity.ok(dentistaService.salvar(dentista));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscarId (@PathVariable Integer id){
        ResponseEntity<Dentista> response;
        if (dentistaService.buscarPorId(id).isPresent()){
            response = new ResponseEntity(dentistaService.buscarPorId(id),HttpStatus.OK);
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }


    @PutMapping
    public  ResponseEntity<Dentista> atualizar(@RequestBody Dentista dentista){
        ResponseEntity<Dentista> response;
        if(dentistaService.buscarPorId(dentista.getId()).isPresent()){
            response = ResponseEntity.ok(dentistaService.atualizar(dentista));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    private static boolean validarDentista(Dentista dentista){
       return Objects.nonNull(dentista.getNome()) &&
               !dentista.getNome().isBlank() &&
               !dentista.getNome().isEmpty() &&
               Objects.nonNull(dentista.getSobrenome()) &&
               !dentista.getSobrenome().isEmpty() &&
               !dentista.getSobrenome().isBlank() &&
               Objects.nonNull(dentista.getMatricularCadastro());
    }


}
