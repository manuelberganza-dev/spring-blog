package com.manuelberganza.blog.service;

import com.manuelberganza.blog.model.Categoria;
import com.manuelberganza.blog.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoria {

    @Autowired
    CategoriaRepository categoriaRepo;

    @Override
    public List<Categoria> buscarTodas() {
        return categoriaRepo.findAll();
    }
}
