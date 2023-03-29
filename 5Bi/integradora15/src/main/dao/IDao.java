package main.dao;

import java.util.List;

public interface IDao<T> {
    T salvar(T t);
    List<T> buscarTodos();
    void excluir(int id);
    T buscarPorId(int id);
}
