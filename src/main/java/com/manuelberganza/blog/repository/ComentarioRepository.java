package com.manuelberganza.blog.repository;

import com.manuelberganza.blog.model.Comentario;
import com.manuelberganza.blog.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    public List<Comentario> findByPublicacion(Publicacion publicacion);
}
