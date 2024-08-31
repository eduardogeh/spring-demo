package com.app.demo.repository;

import com.app.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2")
    Usuario findByEmailAndSenha(String email, String senha);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String username);

    List<Usuario> findAllByRole(String role);

    @Query("SELECT pm.paciente FROM PacienteMonitoramento pm where pm.monitor.idUsuario = ?1")
    List<Usuario> findAllMonitorados(Integer idProfissional);
}
