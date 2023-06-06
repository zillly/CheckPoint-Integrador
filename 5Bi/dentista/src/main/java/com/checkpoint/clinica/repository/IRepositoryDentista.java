package com.checkpoint.clinica.repository;

import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryDentista extends JpaRepository<Dentista, Integer> {
    Dentista findDentistaByNomeContainingIgnoreCase(String nome);
    Dentista findDentistaById(Integer id);
}
