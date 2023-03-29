package com.checkpoint.clinica.repository;

import com.checkpoint.clinica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryUsuario extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findUsuarioByNomeContainingIgnoreCase(String nome);
}
