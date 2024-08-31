package com.app.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "status", indexes = {
        @Index(columnList = "id_paciente", name = "status_id_paciente_idx"),
        @Index(columnList = "data_recebimento", name = "status_data_recebimento_idx")
}, uniqueConstraints = {
        @UniqueConstraint(name = "status_unico_id_paciente_data_recebimento", columnNames = {"id_paciente", "data_recebimento"})
}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idStatus")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_status")
    private Integer idStatus;

    @Column(name = "data_recebimento")
    private LocalDateTime dataRecebimento;

    @JoinColumn(name = "id_paciente", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario paciente;

    @Column(name = "dados_json")
    private String dadosJson;

}
