package com.checkpoint.clinica.controller;


import com.checkpoint.clinica.model.Usuario;
import com.checkpoint.clinica.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarId (@PathVariable Integer id){
        ResponseEntity<Usuario> response;
        if (usuarioService.buscarPorId(id).isPresent()){
            response = new ResponseEntity(usuarioService.buscarPorId(id),HttpStatus.OK);
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
       return response;
    }

    @GetMapping
    private ResponseEntity<List<Usuario>> listaTodos(){
    return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletar(@PathVariable Integer id) {
        ResponseEntity<String> response;
        if (usuarioService.buscarPorId(id).isPresent()){
            usuarioService.excluir(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("paciente apagado");
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PostMapping
    public ResponseEntity <Usuario> cadastro(@RequestBody Usuario usuario){
        ResponseEntity<Usuario> response;
        if(validarUsuario(usuario)){
            response = ResponseEntity.ok(usuarioService.salvar(usuario));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
        ResponseEntity<Usuario> response;
        if(usuarioService.buscarPorId(usuario.getId()).isPresent()){
            response = ResponseEntity.ok(usuarioService.atualizar(usuario));
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }


    private static boolean validarUsuario(Usuario usuario){
        return Objects.nonNull(usuario.getNome())   &&
                !usuario.getNome().isBlank() &&
                !usuario.getNome().isEmpty() &&
                Objects.nonNull(usuario.getEmail()) &&
                !usuario.getEmail().isBlank() &&
                !usuario.getEmail().isEmpty() &&
                Objects.nonNull(usuario.getSenha()) &&
                !usuario.getSenha().isBlank() &&
                !usuario.getSenha().isEmpty() &&
                Objects.nonNull(usuario.getNivelAcesso()) &&
                !usuario.getNivelAcesso().isEmpty() &&
                !usuario.getNivelAcesso().isBlank();
    }

}
