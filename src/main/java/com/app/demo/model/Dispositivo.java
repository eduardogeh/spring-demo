package com.app.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dispositivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idDispositivo")
public class Dispositivo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dispositivo")
    private Integer idDispositivo;

    @JoinColumn(name = "id_paciente", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario paciente;

}
