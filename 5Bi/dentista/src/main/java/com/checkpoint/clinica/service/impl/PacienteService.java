package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.model.Paciente;
import com.checkpoint.clinica.repository.IRepositoryPaciente;
import com.checkpoint.clinica.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<Paciente> {

    private IRepositoryPaciente repository;
    final static Logger log = Logger.getLogger(PacienteService.class);
    private EnderecoService enderecoService;

    @Autowired
    public PacienteService(IRepositoryPaciente repository, EnderecoService enderecoService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        log.debug("Registrando paciente : " + paciente.toString());
        return repository.save(paciente);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void excluir(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Paciente> buscarPorId(int id) {
        log.debug("Buscando paciente com id  : " + id);
       return repository.findById(id);
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        log.debug("Atualizando um paciente: " + paciente.toString());
        return repository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorNome(String nome) {
        return repository.findPacienteByNomeContainingIgnoreCase(nome);
    }


}
