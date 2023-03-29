package com.checkpoint.clinica.controller;

import com.checkpoint.clinica.model.Consulta;
import com.checkpoint.clinica.service.impl.ConsultaService;
import com.checkpoint.clinica.service.impl.DentistaService;
import com.checkpoint.clinica.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
      private ConsultaService consultaService;
    @Autowired
      private DentistaService dentistaService;
    @Autowired
      private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarId (@PathVariable Integer id){
        ResponseEntity<Consulta> response;
        if (consultaService.buscarPorId(id).isPresent()){
            response = new ResponseEntity(consultaService.buscarPorId(id),HttpStatus.OK);
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Consulta> cadastro(@RequestBody Consulta consulta){
         ResponseEntity<Consulta> response;
         if(pacienteService.buscarPorId(consulta.getPaciente().getId()).isPresent()
         && dentistaService.buscarPorId(consulta.getDentista().getId()).isPresent()){
             response = ResponseEntity.ok(consultaService.salvar(consulta));
         }else {
             response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }
         return response;
     }

     @GetMapping
    public ResponseEntity<List<Consulta>>buscarTodos(){
         return ResponseEntity.ok(consultaService.buscarTodos());
     }


     @PutMapping
      public  ResponseEntity<Consulta> atualizar(@RequestBody Consulta consulta){
         ResponseEntity<Consulta> response;
         if(consultaService.buscarPorId(consulta.getId()).isPresent()){
             response = ResponseEntity.ok(consultaService.atualizar(consulta));
         }else {
             response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }
         return response;
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id){
         ResponseEntity<String> response;
         if (consultaService.buscarPorId(id).isPresent()){
             consultaService.excluir(id);
             response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Consulta apagada");
         }else {
             response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }
         return response;
    }


}
