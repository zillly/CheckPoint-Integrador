package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.repository.IRepositoryDentista;
import com.checkpoint.clinica.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DentistaService implements IService<Dentista> {

    private IRepositoryDentista repository;
    final static Logger log = Logger.getLogger(DentistaService.class);

    @Autowired
    public DentistaService(IRepositoryDentista repository) {
        this.repository = repository;
    }

    @Override
    public Dentista salvar(Dentista dentista) {
        log.debug("Registrando novo dentista: " + dentista.toString());
        return repository.save(dentista);
    }


    @Override
    public List<Dentista> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void excluir(int id) {
        repository.deleteById(id);

    }

    @Override
    public Optional<Dentista> buscarPorId(int id) {
        return repository.findById(id);
    }

    @Override
    public Dentista atualizar(Dentista dentista) {
        log.debug("Atualizando um paciente: " + dentista.toString());
        return repository.save(dentista);
    }

    @Override
    public Optional<Dentista> buscarPorNome(String nome) {
        return repository.findDentistaByNomeContainingIgnoreCase(nome);
    }


}
