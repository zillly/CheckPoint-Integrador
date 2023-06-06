package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.controller.dto.EnderecoResponse;
import com.checkpoint.clinica.model.Endereco;
import com.checkpoint.clinica.repository.IRepositoryEndereco;
import com.checkpoint.clinica.service.IEnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class EnderecoService implements IEnderecoService {


    private IRepositoryEndereco repository;

    ObjectMapper mapper = new ObjectMapper();

    final static Logger log = Logger.getLogger(EnderecoService.class);

    @Autowired
    public EnderecoService(IRepositoryEndereco repository) {
        this.repository = repository;
    }

    @Override
    public EnderecoResponse salvar(Endereco endereco) {
        log.debug("Registrando endereco : " + endereco.toString());
        ObjectMapper mapper = new ObjectMapper();
        Endereco enderecoSalvo = repository.save(endereco);
        EnderecoResponse enderecoResponse = mapper.convertValue(enderecoSalvo , EnderecoResponse.class);
        return enderecoResponse;

    }

    @Override
    public List<EnderecoResponse> buscarTodos() {
        log.debug("Buscar Todos");
        List<Endereco> enderecos = repository.findAll();
        List<EnderecoResponse> response = new ArrayList<>();
        for (Endereco endereco : enderecos){
            response.add(mapper.convertValue(endereco, EnderecoResponse.class));
        }
        return response;
    }

    @Override
    public void excluir(int id) {
      repository.deleteById(id);
    }

    @Override
    public EnderecoResponse buscarPorId(int id) {
        log.debug("Buscar Por ID");
        Endereco endereco = repository.findEnderecoById(id);
        EnderecoResponse enderecoResponse = mapper.convertValue(endereco , EnderecoResponse.class);
        return enderecoResponse;
    }

    @Override
    public EnderecoResponse atualizar(Endereco endereco) {
        log.debug("Atualizar : " + endereco.toString());
        Endereco enderecoSalvo = repository.save(endereco);
        EnderecoResponse enderecoResponse = mapper.convertValue(enderecoSalvo , EnderecoResponse.class);
        return enderecoResponse;
    }


}
