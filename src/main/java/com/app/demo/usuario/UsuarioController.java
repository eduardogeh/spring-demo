package com.app.demo.usuario;

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

    @PostMapping("/editar")
    public void editar(@RequestBody UsuarioDto usuarioDto) {
        usuarioServico.editar(usuarioDto);
    }

}
