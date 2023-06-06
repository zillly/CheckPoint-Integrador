package com.checkpoint.clinica.repository;

import com.checkpoint.clinica.model.Consulta;
import com.checkpoint.clinica.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryConsulta extends JpaRepository<Consulta, Integer> {
   Consulta findConsultaByPacienteNomeContainingIgnoreCase(String nome);
   Consulta findConsultaById(Integer id);
}
