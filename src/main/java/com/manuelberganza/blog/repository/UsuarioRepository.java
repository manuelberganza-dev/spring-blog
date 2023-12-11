package com.manuelberganza.blog.repository;

import com.manuelberganza.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);
}
