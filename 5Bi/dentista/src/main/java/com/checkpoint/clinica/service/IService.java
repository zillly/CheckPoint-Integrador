package com.checkpoint.clinica.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IService<T>{

    T salvar(T t);
    List<T> buscarTodos();
    void excluir(int id);
    Optional<T> buscarPorId(int id);

    T atualizar (T t);

    Optional<T> buscarPorNome(String nome);

}
