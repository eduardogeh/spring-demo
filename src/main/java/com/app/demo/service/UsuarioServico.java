package com.app.demo.service;

import com.app.demo.controller.MonitoramentoController;
import com.app.demo.model.Usuario;
import com.app.demo.pojo.UsuarioDto;
import com.app.demo.repository.PacienteMonitoramentoRepository;
import com.app.demo.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsuarioServico {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PacienteMonitoramentoRepository pacienteMonitoramentoRepository;

    public List<UsuarioDto> lista() {
        List<Usuario> usuariosList = usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtoList = new ArrayList<>();

        for(Usuario usuario : usuariosList){
            usuarioDtoList.add(
                    UsuarioDto.builder()
                            .id(usuario.getIdUsuario())
                            .email(usuario.getUsername())
                            .role(usuario.getRole())
                            .build()
            );
        }

        return usuarioDtoList;
    }

    public void delete(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public void editar(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.getById(usuarioDto.getId());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setRole(usuarioDto.getRole());
        usuarioRepository.save(usuario);
    }

    public List<UsuarioDto> listaPacientes() {
        try {
            return usuarioRepository.findAllByRole("paciente").stream().map(
                    usuario -> UsuarioDto.builder()
                            .id(usuario.getIdUsuario())
                            .email(usuario.getUsername())
                            .role(usuario.getRole())
                            .build()
            ).toList();
        } catch (Exception e) {
            log.error("Erro ao buscar pacientes", e);
            return null;
        }

    }

    public List<UsuarioDto> listaPacientesPorProfissional(Integer idProfissional) {
        try {
            return usuarioRepository.findAllMonitorados(idProfissional).stream().map(
                    usuario -> UsuarioDto.builder()
                            .id(usuario.getIdUsuario())
                            .email(usuario.getUsername())
                            .role(usuario.getRole())
                            .build()
            ).toList();
        } catch (Exception e) {
            log.error("Erro ao buscar pacientes por profissional", e);
            return null;
        }
    }

    public UsuarioDto getById(Integer idUsuario) {
        Usuario user = usuarioRepository.findById(idUsuario).orElseThrow();
        return UsuarioDto.builder()
                .id(user.getIdUsuario())
                .email(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public List<UsuarioDto> listarProfissionaisPorPaciente(Integer idPaciente) {
        try {
            return pacienteMonitoramentoRepository.findAllByPacienteIdUsuario(idPaciente).stream().map(
                    pacienteMonitoramento -> UsuarioDto.builder()
                            .id(pacienteMonitoramento.getMonitor().getIdUsuario())
                            .email(pacienteMonitoramento.getMonitor().getUsername())
                            .nome(pacienteMonitoramento.getMonitor().getNome())
                            .role(pacienteMonitoramento.getMonitor().getRole())
                            .build()
            ).toList();
        } catch (Exception e) {
            log.error("Erro ao buscar profissionais por paciente", e);
            return null;
        }
    }
}

