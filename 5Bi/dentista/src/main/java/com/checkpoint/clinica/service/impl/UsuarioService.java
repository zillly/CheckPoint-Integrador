package com.checkpoint.clinica.service.impl;


import com.checkpoint.clinica.controller.dto.UsuarioRequest;
import com.checkpoint.clinica.controller.dto.UsuarioResponse;
import com.checkpoint.clinica.exeption.InvalidDataException;
import com.checkpoint.clinica.exeption.ResourceNotFoundException;
import com.checkpoint.clinica.model.Usuario;
import com.checkpoint.clinica.repository.IRepositoryUsuario;
import com.checkpoint.clinica.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

public class UsuarioService implements IUsuarioService {

    private IRepositoryUsuario repository;
    final static Logger log = Logger.getLogger(UsuarioService.class);
    ObjectMapper mapper = new ObjectMapper();

    public UsuarioService(IRepositoryUsuario repository) {
        this.repository = repository;
    }

    @Override
    public UsuarioResponse salvar(UsuarioRequest request) throws InvalidDataException {
        log.debug("Salvando novo Usuario : "+ request.toString());
        if (validarUsuario(request)){
            Usuario usuario = toUsuario (request);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaCriptografada = encoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            Usuario usuarioSalvo = repository.save(usuario);
            return toUsuarioRequest(usuarioSalvo);

        } else {
            throw new InvalidDataException("Não foi possível cadastrar o usuario");
        }
    }

    @Override
    public List<UsuarioResponse> buscarTodos() {
        log.debug("Buscando Todos");
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponse> response = new ArrayList<>();
        for (Usuario usuario : usuarios){
            response.add(mapper.convertValue(usuario, UsuarioResponse.class));
        }
        return response;
    }


    @Override
    public UsuarioResponse buscarPorId(Integer id) throws ResourceNotFoundException {
        log.debug("Buscando usuario com id : " + id);
         Usuario byId = repository.findUsuarioById(id);
        if (byId != null){
            return  mapper.convertValue(byId,UsuarioResponse.class);
        }else {
            throw new ResourceNotFoundException("Usuario não encontrado");
        }
    }

    @Override
    public UsuarioResponse atualizar(Usuario usuario) {
        log.debug("Atualizando um usuario: " + usuario.toString());
        Usuario usuarioSalvo = repository.save(usuario);
        UsuarioResponse usuarioResponse = mapper.convertValue(usuarioSalvo, UsuarioResponse.class);
        return usuarioResponse;
    }

    @Override
    public UsuarioResponse buscarPorNome(String nome) {
        log.debug("Buscando Por Nome");
        Usuario buscar = repository.findUsuarioByNomeContainingIgnoreCase(nome);
        UsuarioResponse usuarioResponse = mapper.convertValue(buscar ,UsuarioResponse.class);
        return usuarioResponse;
    }


    @Override
    public void excluir(int id) {
        log.debug("Deletar");
        repository.deleteById(id);
    }

    private UsuarioResponse toUsuarioRequest(Usuario usuario){
        return UsuarioResponse.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .nivelAcesso(usuario.getNivelAcesso())
                .build();
    }

    private Usuario toUsuario(UsuarioRequest usuario){
        return Usuario.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .nivelAcesso(usuario.getNivelAcesso())
                .build();
    }

    private static boolean validarUsuario(UsuarioRequest usuario) {
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
