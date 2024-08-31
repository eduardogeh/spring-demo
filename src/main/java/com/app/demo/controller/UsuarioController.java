package com.app.demo.controller;

import com.app.demo.pojo.UsuarioDto;
import com.app.demo.service.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServico usuarioServico;

    @GetMapping("/lista")
    public List<UsuarioDto> lista() {
        return usuarioServico.lista();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer idUsuario) {
        usuarioServico.delete(idUsuario);
    }

    @GetMapping("/{id}")
    public UsuarioDto getById(@PathVariable("id") Integer idUsuario) {
        return usuarioServico.getById(idUsuario);
    }

    @PostMapping("/editar")
    public void editar(@RequestBody UsuarioDto usuarioDto) {
        usuarioServico.editar(usuarioDto);
    }

    @GetMapping("pacientes/lista")
    public List<UsuarioDto> listaPacientes() {
        return usuarioServico.listaPacientes();
    }

    @GetMapping("pacientes/{idProfissional}")
    public List<UsuarioDto> listaPacientes(@PathVariable("idProfissional") Integer idProfissional) {
        return usuarioServico.listaPacientesPorProfissional(idProfissional);
    }

    @GetMapping("profissionais/{idPaciente}")
    public List<UsuarioDto> listarProfissionaisPorPaciente(@PathVariable("idPaciente") Integer idPaciente) {
        return usuarioServico.listarProfissionaisPorPaciente(idPaciente);
    }

}
