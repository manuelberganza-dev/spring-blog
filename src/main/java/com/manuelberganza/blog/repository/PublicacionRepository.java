package com.manuelberganza.blog.repository;

import com.manuelberganza.blog.model.Publicacion;
import com.manuelberganza.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    public List<Publicacion> findByUsuario(Usuario usuario);
}
