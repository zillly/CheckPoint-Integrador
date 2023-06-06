package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.controller.dto.DentistaResponse;
import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.repository.IRepositoryDentista;
import com.checkpoint.clinica.service.IDentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class DentistaService implements IDentistaService {

    private IRepositoryDentista repository;

    ObjectMapper mapper = new ObjectMapper();
    final static Logger log = Logger.getLogger(DentistaService.class);

    @Autowired
    public DentistaService(IRepositoryDentista repository) {
        this.repository = repository;
    }

    @Override
    public DentistaResponse salvar(Dentista dentista) {
        log.debug("Registrando novo dentista: " + dentista.toString());
        ObjectMapper mapper = new ObjectMapper();
        Dentista salvo = repository.save(dentista);
        DentistaResponse dentistaResponse = mapper.convertValue(salvo , DentistaResponse.class);
        return dentistaResponse;
    }


    @Override
    public List<DentistaResponse> buscarTodos() {
        log.debug("Buscando Todos");
        List<Dentista> dentistas = repository.findAll();
        List<DentistaResponse> response = new ArrayList<>();
        for (Dentista dentista : dentistas){
            response.add(mapper.convertValue(dentista, DentistaResponse.class));
        }
        return response;
    }

    @Override
    public void excluir(int id) {
        log.debug("Deletetando");
        repository.deleteById(id);

    }

    @Override
    public DentistaResponse buscarPorId(int id) {
        log.debug("Buscando Por ID");
       Dentista dentista = repository.findDentistaById(id);
        DentistaResponse dentistaResponse = mapper.convertValue(dentista , DentistaResponse.class);
        return dentistaResponse;

    }

    @Override
    public DentistaResponse atualizar(Dentista dentista) {
        log.debug("Atualizando um paciente: " + dentista.toString());
        Dentista salvo = repository.save(dentista);
        DentistaResponse dentistaResponse = mapper.convertValue(salvo , DentistaResponse.class);
        return dentistaResponse;
    }

    @Override
    public DentistaResponse buscarPorNome(String nome) {
        log.debug("Buscando Por Nome");
       Dentista buscar = repository.findDentistaByNomeContainingIgnoreCase(nome);
        DentistaResponse dentistaResponse = mapper.convertValue(buscar , DentistaResponse.class);
        return dentistaResponse;
    }


}
