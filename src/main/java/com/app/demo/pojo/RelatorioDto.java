package com.app.demo.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioDto {
    private String label;
    private Integer value;
}
