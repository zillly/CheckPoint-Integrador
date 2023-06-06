package com.checkpoint.clinica.service;

import com.checkpoint.clinica.controller.dto.UsuarioRequest;
import com.checkpoint.clinica.controller.dto.UsuarioResponse;
import com.checkpoint.clinica.exeption.InvalidDataException;
import com.checkpoint.clinica.exeption.ResourceNotFoundException;
import com.checkpoint.clinica.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {

    UsuarioResponse salvar(UsuarioRequest usuario) throws InvalidDataException;
    List<UsuarioResponse> buscarTodos();
    void excluir(int id);
    UsuarioResponse buscarPorId(Integer id) throws ResourceNotFoundException;

    UsuarioResponse atualizar (Usuario usuario);

    UsuarioResponse buscarPorNome(String nome);

}
