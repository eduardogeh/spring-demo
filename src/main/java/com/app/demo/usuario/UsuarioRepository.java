package com.app.demo.usuario;

import com.app.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2")
    Usuario findByEmailAndSenha(String email, String senha);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String username);
}
