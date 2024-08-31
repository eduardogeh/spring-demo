package com.app.demo.pojo;

import com.app.demo.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    private Integer id;
    private String email;
    private RoleEnum role;
    private String nome;
}
