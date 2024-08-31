package com.app.demo.repository;

import com.app.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query(value = "SELECT s FROM status s join usuario u on s.id_paciente = u.id_usuario WHERE s.data_recebimento >= (NOW() - ?1\\:\\:interval) AND u.id_usuario = ?2", nativeQuery = true)
    List<Status> getDadosRelatorios(String diasAnterioresBuscar, Integer idPaciente);
}
