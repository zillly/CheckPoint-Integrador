package com.checkpoint.clinica.service;

import com.checkpoint.clinica.controller.dto.EnderecoResponse;
import com.checkpoint.clinica.model.Endereco;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEnderecoService {

    EnderecoResponse salvar(Endereco t);
    List<EnderecoResponse> buscarTodos();
    void excluir(int id);
    EnderecoResponse buscarPorId(int id);

    EnderecoResponse atualizar (Endereco t);



}
