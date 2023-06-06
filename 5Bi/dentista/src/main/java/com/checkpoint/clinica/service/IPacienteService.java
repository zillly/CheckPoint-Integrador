package com.checkpoint.clinica.service;

import com.checkpoint.clinica.controller.dto.PacienteResponse;
import com.checkpoint.clinica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {

    PacienteResponse salvar(Paciente paciente);

    List<PacienteResponse> buscarTodos();

    void excluir(int id);

    PacienteResponse buscarPorId(int id);

    PacienteResponse atualizar (Paciente t);

    PacienteResponse buscarPorNome(String nome);

}
