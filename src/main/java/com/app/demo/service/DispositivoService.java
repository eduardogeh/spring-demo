package com.app.demo.service;

import com.app.demo.model.Usuario;
import com.app.demo.pojo.DispositivoDto;
import com.app.demo.pojo.UsuarioDto;
import com.app.demo.repository.DispositivoRepository;
import com.app.demo.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public List<DispositivoDto> getDispositivoByPaciente(Integer idPaciente) {
        return dispositivoRepository.findByPacienteIdUsuario(idPaciente).stream().map(
                dispositivo -> DispositivoDto.builder()
                        .idDispositivo(dispositivo.getIdDispositivo())
                        .idUsuarioDono(dispositivo.getPaciente().getIdUsuario())
                        .nomeUsuarioDono(dispositivo.getPaciente().getNome())
                        .build()).toList();
    }
}

