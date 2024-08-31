package com.app.demo.repository;

import com.app.demo.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {

    List<Dispositivo> findByPacienteIdUsuario(Integer idPaciente);
}
