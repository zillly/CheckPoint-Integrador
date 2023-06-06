package com.checkpoint.clinica.repository;

import com.checkpoint.clinica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryUsuario extends JpaRepository<Usuario, Integer> {
    Usuario findUsuarioByNomeContainingIgnoreCase(String nome);
    Usuario findUsuarioById(Integer id);

    UserDetails findUsuarioByEmail(String subject);
}
