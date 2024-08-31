package com.app.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "paciente_monitoramento", indexes = {
        @Index(columnList = "id_paciente", name = "paciente_monitoramento_id_paciente_idx"),
        @Index(columnList = "id_monitor", name = "paciente_monitoramento_id_monitor_idx"),
        @Index(columnList = "data_inicio_monitoramento", name = "paciente_monitoramento_data_inicio_monitoramento_idx")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "paciente_monitoramento_unico_id_paciente_id_monitor", columnNames = {"id_paciente", "id_monitor"}),
                @UniqueConstraint(name = "paciente_monitoramento_unico_codigo_convite", columnNames = {"codigo_convite"})
        }
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPacienteMonitoramento")
public class PacienteMonitoramento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paciente_monitoramento")
    private Integer idPacienteMonitoramento;

    @JoinColumn(name = "id_paciente", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario paciente;

    @JoinColumn(name = "id_monitor", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario monitor;

    @Column(name = "data_inicio_monitoramento")
    private LocalDateTime dataInicioMonitoramento;

    @Column(name = "codigo_convite")
    private String codigoConvite;

}
