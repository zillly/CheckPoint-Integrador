package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.controller.dto.ConsultaResponse;
import com.checkpoint.clinica.model.Consulta;
import com.checkpoint.clinica.repository.IRepositoryConsulta;
import com.checkpoint.clinica.service.IConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ConsultaService implements IConsultaService {

    private IRepositoryConsulta repository;

    ObjectMapper mapper = new ObjectMapper();
    final static Logger log = Logger.getLogger(ConsultaService.class);

    @Autowired
    public ConsultaService(IRepositoryConsulta repository) {
        this.repository = repository;
    }

    @Override
    public ConsultaResponse salvar(Consulta consulta) {
        log.debug("Registrando novo consulta: " + consulta.toString());
        mapper.registerModule(new JavaTimeModule());
        Consulta salvo = repository.save(consulta);
        ConsultaResponse consultaResponse = mapper.convertValue(salvo , ConsultaResponse.class);
        return consultaResponse;

    }

    @Override
    public List<ConsultaResponse> buscarTodos() {
        log.debug("Buscando Todos");
        List<Consulta> consultas = repository.findAll();
        List<ConsultaResponse> response = new ArrayList<>();
        for (Consulta consulta : consultas){
            response.add(mapper.convertValue(consulta, ConsultaResponse.class));
        }
        return response;
    }

    @Override
    public void excluir(int id) {
        repository.deleteById(id);
    }

    @Override
    public ConsultaResponse buscarPorId(int id) {
        log.debug("Buscando Por ID");
        Consulta consulta = repository.findConsultaById(id);
        ConsultaResponse consultaMapper = mapper.convertValue(consulta , ConsultaResponse.class);
        return consultaMapper;
    }

    @Override
    public ConsultaResponse atualizar(Consulta consulta) {
        log.debug("Atualizar consulta: " + consulta.toString());
        Consulta salvo = repository.save(consulta);
        ConsultaResponse consultaResponse = mapper.convertValue(salvo , ConsultaResponse.class);
        return consultaResponse;
    }

    @Override
    public ConsultaResponse buscarPorNome(String nome) {
        log.debug("Buscando Por Nome");
        Consulta consulta = repository.findConsultaByPacienteNomeContainingIgnoreCase(nome);
        ConsultaResponse consultaMapper = mapper.convertValue(consulta , ConsultaResponse.class);
        return consultaMapper;
    }


}
