package com.manuelberganza.blog.service;

import com.manuelberganza.blog.model.Publicacion;
import com.manuelberganza.blog.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPublicaciones {
    public void guardar(Publicacion publicacion);
    public List<Publicacion> buscarTodasPorId(Usuario usuario);
    public Publicacion buscarPorId(Integer id);
    public Page<Publicacion> buscarTodasPaginada(Pageable page);
    public void eliminarPublicacion(Integer id);
}
