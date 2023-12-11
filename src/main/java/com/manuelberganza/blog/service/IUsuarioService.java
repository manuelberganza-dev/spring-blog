package com.manuelberganza.blog.service;

import com.manuelberganza.blog.model.Usuario;

public interface IUsuarioService {
    public void guardarUsuario(Usuario usuario);
    public Usuario buscarPorNombre(String nombre);
    public Boolean validarUsername(String username);
    public Boolean validarEmail(String email);
}
