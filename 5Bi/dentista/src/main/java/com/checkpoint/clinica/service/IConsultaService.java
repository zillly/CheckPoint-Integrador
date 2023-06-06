package com.checkpoint.clinica.service;

import com.checkpoint.clinica.controller.dto.ConsultaResponse;
import com.checkpoint.clinica.model.Consulta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IConsultaService {

    ConsultaResponse salvar(Consulta consulta);
    List<ConsultaResponse> buscarTodos();
    void excluir(int id);
    ConsultaResponse buscarPorId(int id);

    ConsultaResponse atualizar (Consulta consulta);

   ConsultaResponse buscarPorNome(String nome);

}
