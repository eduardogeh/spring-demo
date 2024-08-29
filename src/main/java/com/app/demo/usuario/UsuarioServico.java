package com.app.demo.usuario;

import com.app.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
}

