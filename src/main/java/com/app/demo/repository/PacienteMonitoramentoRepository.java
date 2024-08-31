package com.app.demo.repository;

import com.app.demo.model.PacienteMonitoramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteMonitoramentoRepository extends JpaRepository<PacienteMonitoramento, Integer> {

    void deleteAllByPacienteIdUsuarioAndMonitorIdUsuario(Integer idPaciente, Integer idMonitor);

    PacienteMonitoramento findByCodigoConvite(String uuid);

    List<PacienteMonitoramento> findAllByPacienteIdUsuario(Integer idPaciente);
}
