package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.model.Consulta;
import com.checkpoint.clinica.repository.IRepositoryConsulta;
import com.checkpoint.clinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ConsultaService implements IService<Consulta> {

    private IRepositoryConsulta repository;

    @Autowired
    public ConsultaService(IRepositoryConsulta repository) {
        this.repository = repository;
    }

    @Override
    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);

    }

    @Override
    public List<Consulta> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void excluir(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Consulta> buscarPorId(int id) {
        return repository.findById(id);
    }

    @Override
    public Consulta atualizar(Consulta consulta) {
        return repository.save(consulta);
    }

    @Override
    public Optional<Consulta> buscarPorNome(String nome) {
        return Optional.empty();
    }
}
