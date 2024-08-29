package com.app.demo.usuario;

public enum RoleEnum {
    ADMIN("admin"),
    NORMAL_USER("user");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
