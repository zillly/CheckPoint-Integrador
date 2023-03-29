package com.checkpoint.clinica.repository;

import com.checkpoint.clinica.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryDentista extends JpaRepository<Dentista, Integer> {
    Optional<Dentista> findDentistaByNomeContainingIgnoreCase(String nome);
}
