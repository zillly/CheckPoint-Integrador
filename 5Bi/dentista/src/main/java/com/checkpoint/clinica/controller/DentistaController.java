package com.checkpoint.clinica.controller;

import com.checkpoint.clinica.controller.dto.DentistaResponse;
import com.checkpoint.clinica.controller.dto.PacienteResponse;
import com.checkpoint.clinica.exeption.ResourceNotFoundException;
import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.service.impl.DentistaService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Listar dados todos os dentistas")
    @GetMapping
    public ResponseEntity<List<DentistaResponse>>listarTodas() throws ResourceNotFoundException {
        List<DentistaResponse> dentistaResponses = dentistaService.buscarTodos();
        if (!dentistaResponses.isEmpty()) {
            return ResponseEntity.ok(dentistaResponses);
        } else {
            throw new ResourceNotFoundException("Erro ao gerar a lista");
        }
    }
    @Operation(summary = "Excluir dentista pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) throws ResourceNotFoundException {
        if (Objects.nonNull(dentistaService.buscarPorId(id))){
            dentistaService.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("dentista apagado");
        }else {
            throw new ResourceNotFoundException("Erro ao deletar Dentista");
        }
    }
    @Operation(summary = "Procurar pelo nome do dentista")
    @GetMapping("/nome/{nome}")
    public ResponseEntity <DentistaResponse>buscarPorNome(@PathVariable String nome ) throws ResourceNotFoundException {
        if (Objects.nonNull(dentistaService.buscarPorNome(nome))){
            return ResponseEntity.ok(dentistaService.buscarPorNome(nome));
        }else {
            throw new ResourceNotFoundException("Erro ao buscar Por Nome Dentista");
        }
    }
    @Operation(summary = "Cadastrar um novo destista")
    @PostMapping
    public ResponseEntity<DentistaResponse> cadastrar(@RequestBody Dentista dentista) throws ResourceNotFoundException {
        if(validarDentista(dentista)){
            return ResponseEntity.ok(dentistaService.salvar(dentista));
        }else {
            throw new ResourceNotFoundException("Erro ao buscar Por Nome Dentista");
        }
    }
    @Operation(summary = "Procurar pelo id do dentista")
    @GetMapping("/{id}")
    public ResponseEntity<DentistaResponse> buscarId (@PathVariable Integer id) throws ResourceNotFoundException {
        if (Objects.nonNull(dentistaService.buscarPorId(id))){
           return new ResponseEntity(dentistaService.buscarPorId(id),HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Erro ao buscar Por id Dentista");
        }
    }

    @Operation(summary = "Atualizar dados do dentista")
    @PutMapping
    public  ResponseEntity<DentistaResponse> atualizar(@RequestBody Dentista dentista) throws ResourceNotFoundException {
        if(validarDentista(dentista)){
            return ResponseEntity.ok(dentistaService.atualizar(dentista));
        }else {
            throw new ResourceNotFoundException("Erro ao buscar Por id Dentista");
        }
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
