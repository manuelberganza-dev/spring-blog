package com.manuelberganza.blog.service;

import com.manuelberganza.blog.model.Comentario;
import com.manuelberganza.blog.model.Publicacion;

import java.util.List;

public interface IComentario {
    public void guardarComentario(Comentario comentario);
    public List<Comentario> buscarTodos();
    public List<Comentario> bucarPorPublicacion(Publicacion publicacion);
}
