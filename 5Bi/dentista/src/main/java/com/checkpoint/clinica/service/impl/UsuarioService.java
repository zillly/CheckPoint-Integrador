package com.checkpoint.clinica.service.impl;


import com.checkpoint.clinica.model.Usuario;
import com.checkpoint.clinica.repository.IRepositoryUsuario;
import com.checkpoint.clinica.service.IService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService implements IService<Usuario> {

    private IRepositoryUsuario repository;
    final static Logger log = Logger.getLogger(UsuarioService.class);

    public UsuarioService(IRepositoryUsuario repository) {
        this.repository = repository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }


    @Override
    public Optional<Usuario> buscarPorId(int id) {
        log.debug("Buscando usuario com id : " + id);
        return  repository.findById(id);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {

        log.debug("Atualizando um usuario: " + usuario.toString());
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorNome(String nome) {
        return repository.findUsuarioByNomeContainingIgnoreCase(nome);
    }


    @Override
    public void excluir(int id) {
        repository.deleteById(id);
    }




}
