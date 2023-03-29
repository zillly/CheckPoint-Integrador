package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.model.Endereco;
import com.checkpoint.clinica.repository.IRepositoryEndereco;
import com.checkpoint.clinica.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EnderecoService implements IService<Endereco> {


    private IRepositoryEndereco repository;
    final static Logger log = Logger.getLogger(EnderecoService.class);

    @Autowired
    public EnderecoService(IRepositoryEndereco repository) {
        this.repository = repository;
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    @Override
    public List<Endereco> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void excluir(int id) {
      repository.deleteById(id);
    }

    @Override
    public Optional<Endereco> buscarPorId(int id) {
        return repository.findById(id);
    }

    @Override
    public Endereco atualizar(Endereco endereco) {
        return repository.save(endereco);
    }

    @Override
    public Optional<Endereco> buscarPorNome(String nome) {
        return Optional.empty();
    }
}
