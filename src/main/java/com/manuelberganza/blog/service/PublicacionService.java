package com.manuelberganza.blog.service;

import com.manuelberganza.blog.model.Publicacion;
import com.manuelberganza.blog.model.Usuario;
import com.manuelberganza.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService implements IPublicaciones{

    @Autowired
    PublicacionRepository publicacionRepo;

    @Override
    public void guardar(Publicacion publicacion) {
        publicacionRepo.save(publicacion);
    }

    @Override
    public List<Publicacion> buscarTodasPorId(Usuario usuario) {
        return publicacionRepo.findByUsuario(usuario);
    }

    @Override
    public Publicacion buscarPorId(Integer id) {
        Optional<Publicacion> opt = publicacionRepo.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

         return null;
    }

    @Override
    public Page<Publicacion> buscarTodasPaginada(Pageable page) {
        return publicacionRepo.findAll(page);
    }

    @Override
    public void eliminarPublicacion(Integer id) {
        publicacionRepo.deleteById(id);
    }


}
