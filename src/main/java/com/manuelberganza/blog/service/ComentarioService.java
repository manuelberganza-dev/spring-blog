package com.manuelberganza.blog.service;

import com.manuelberganza.blog.model.Comentario;
import com.manuelberganza.blog.model.Publicacion;
import com.manuelberganza.blog.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService implements IComentario{

    @Autowired
    private ComentarioRepository comentarioRepo;

    @Override
    public void guardarComentario(Comentario comentario) {
        comentarioRepo.save(comentario);
    }

    @Override
    public List<Comentario> buscarTodos() {
        return comentarioRepo.findAll();
    }

    @Override
    public List<Comentario> bucarPorPublicacion(Publicacion publicacion) {
        return comentarioRepo.findByPublicacion(publicacion);
    }
}
