package com.app.demo.utils.login;

import com.app.demo.enums.RoleEnum;

public record ResgisterDto(String email, String senha, RoleEnum role, String nome) {
}
