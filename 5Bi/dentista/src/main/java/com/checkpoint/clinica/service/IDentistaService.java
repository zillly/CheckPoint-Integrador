package com.checkpoint.clinica.service;

import com.checkpoint.clinica.controller.dto.DentistaResponse;
import com.checkpoint.clinica.model.Dentista;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDentistaService {

    DentistaResponse salvar(Dentista t);
    List<DentistaResponse> buscarTodos();
    void excluir(int id);
    DentistaResponse buscarPorId(int id);

    DentistaResponse atualizar (Dentista t);

    DentistaResponse buscarPorNome(String nome);

}
