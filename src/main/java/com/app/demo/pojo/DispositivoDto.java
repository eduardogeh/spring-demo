package com.app.demo.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DispositivoDto {
    private Integer idDispositivo;
    private Integer idUsuarioDono;
    private String nomeUsuarioDono;
}
