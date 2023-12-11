package com.manuelberganza.blog.repository;

import com.manuelberganza.blog.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
