package com.app.demo.enums;

public enum RoleEnum {
    PROFISSIONAL("profissional"),
    PACIENTE("paciente");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
