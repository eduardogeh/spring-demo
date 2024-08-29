package com.app.demo.usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    private Integer id;
    private String email;
    private RoleEnum role;
}
