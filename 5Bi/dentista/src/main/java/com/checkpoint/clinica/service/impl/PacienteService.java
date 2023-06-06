package com.checkpoint.clinica.service.impl;

import com.checkpoint.clinica.controller.dto.EnderecoResponse;
import com.checkpoint.clinica.controller.dto.PacienteResponse;
import com.checkpoint.clinica.model.Endereco;
import com.checkpoint.clinica.model.Paciente;
import com.checkpoint.clinica.repository.IRepositoryPaciente;
import com.checkpoint.clinica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private IRepositoryPaciente repository;

    ObjectMapper mapper = new ObjectMapper();
    final static Logger log = Logger.getLogger(PacienteService.class);
    private EnderecoService enderecoService;

    @Autowired
    public PacienteService(IRepositoryPaciente repository, EnderecoService enderecoService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
    }

    @Override
    public PacienteResponse salvar(Paciente paciente) {
        log.debug("Registrando paciente : " + paciente.toString());
        mapper.registerModule(new JavaTimeModule());
        Paciente pacienteSalvo = repository.save(paciente);
        PacienteResponse pacienteResponse = mapper.convertValue(pacienteSalvo , PacienteResponse.class);
        return pacienteResponse;
    }

    @Override
    public List<PacienteResponse> buscarTodos() {
        log.debug("Buscando Todos os pacientes");
        List<Paciente> pacientes = repository.findAll();
        List<PacienteResponse> response = new ArrayList<>();
        for (Paciente paciente : pacientes){
            response.add(mapper.convertValue(paciente, PacienteResponse.class));
        }
        return response;
    }

    @Override
    public void excluir(int id) {
        log.debug("Excluir Paciente");
        repository.deleteById(id);
    }

    @Override
    public PacienteResponse buscarPorId(int id) {
        log.debug("Buscando paciente com id  : " + id);
        Paciente paciente = repository.findPacienteById(id);
        PacienteResponse pacienteResponse = mapper.convertValue(paciente , PacienteResponse.class);
        return pacienteResponse;
    }

    @Override
    public PacienteResponse atualizar(Paciente paciente) {
        log.debug("Atualizando um paciente: " + paciente.toString());
        Paciente pacienteSalvo = repository.save(paciente);
        PacienteResponse pacienteResponse = mapper.convertValue(pacienteSalvo , PacienteResponse.class);
        return pacienteResponse;
    }

    @Override
    public PacienteResponse buscarPorNome(String nome) {
        log.debug("Buscando Por Nome");
        Paciente buscar = repository.findPacienteByNomeContainingIgnoreCase(nome);
        PacienteResponse pacienteResponse = mapper.convertValue(buscar , PacienteResponse.class);
        return pacienteResponse;
    }
    private PacienteResponse toPacienteReponse(Paciente paciente){
        return PacienteResponse.builder()
                .nome(paciente.getNome())
                .sobrenome(paciente.getSobrenome())
                .rg(paciente.getRg())
                .endereco(toEnderecoReponse(paciente.getEndereco()))
                .build();
    }
    private EnderecoResponse toEnderecoReponse(Endereco endereco){
        return EnderecoResponse.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .build();
    }


}
