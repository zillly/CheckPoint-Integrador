package com.checkpoint.clinica.controller;


import com.checkpoint.clinica.controller.dto.UsuarioRequest;
import com.checkpoint.clinica.controller.dto.UsuarioResponse;
import com.checkpoint.clinica.exeption.InvalidDataException;
import com.checkpoint.clinica.exeption.ResourceNotFoundException;
import com.checkpoint.clinica.model.Paciente;
import com.checkpoint.clinica.model.Usuario;
import com.checkpoint.clinica.security.TokenDTO;
import com.checkpoint.clinica.security.TokenService;
import com.checkpoint.clinica.service.impl.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

  @Autowired
  private AuthenticationManager manager;


    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Procurar pelo id da usuario")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarId(@PathVariable Integer id) throws ResourceNotFoundException {
      return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
    @Operation(summary = "Listar Todos os Usuarios")
    @GetMapping
    private ResponseEntity<List<UsuarioResponse>> listaTodos() throws ResourceNotFoundException {
        List<UsuarioResponse> usuarios = usuarioService.buscarTodos();
        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarios);
        } else {
            throw new ResourceNotFoundException("Não ha Registros de Dentistas");
        }

    }
    @Operation(summary = "Logar pra ter acesso a jwt da requisicão")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioRequest usuario) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());
        Authentication authenticate = manager.authenticate(token);
        String tokenJWT = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
    @Operation(summary = "Deletar o Usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) throws ResourceNotFoundException {
        usuarioService.buscarPorId(id);
        usuarioService.excluir(id);
        return ResponseEntity.ok("Deletado");
    }
    @Operation(summary = "Procurar pelo id da consulta")
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastro(@RequestBody UsuarioRequest usuario) throws  InvalidDataException {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
    }
    @Operation(summary = "Procurar pelo nome do usuario")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<UsuarioResponse> buscarPorNome(@PathVariable String nome) throws ResourceNotFoundException {
        UsuarioResponse usuarioResponse = usuarioService.buscarPorNome(nome);
        if (Objects.nonNull(usuarioResponse)) {
            return ResponseEntity.ok(usuarioResponse);
        } else {
            throw new ResourceNotFoundException("Erro ao Buscar Por nome");
        }

    }
    @Operation(summary = "Atualizar o dado do usuario")
    @PutMapping
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody Usuario usuario) throws ResourceNotFoundException {
        if (validarUsuarioA(usuario)) {
            return ResponseEntity.ok(usuarioService.atualizar(usuario));
        } else {
            throw new ResourceNotFoundException("Erro ao Atualizar");
        }
    }



    private static boolean validarUsuarioA(Usuario usuario) {
        return Objects.nonNull(usuario.getNome()) &&
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
