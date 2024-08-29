package com.app.demo.utils.login;

import com.app.demo.usuario.RoleEnum;

public record ResgisterDto(String email, String senha, RoleEnum role) {
}
