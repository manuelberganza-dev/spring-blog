package com.manuelberganza.blog.service;

import com.manuelberganza.blog.model.Usuario;
import com.manuelberganza.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepo;

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepo.save(usuario);
    }

    @Override
    public Usuario buscarPorNombre(String username) {
        return usuarioRepo.findByUsername(username);
    }

    @Override
    public Boolean validarUsername(String username) {
        Usuario usuario = usuarioRepo.findByUsername(username);

        return usuario == null;
    }

    @Override
    public Boolean validarEmail(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email);

        return usuario == null;
    }

}
