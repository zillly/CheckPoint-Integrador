package com.checkpoint.clinica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(mappedBy = "consulta", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Paciente paciente;
    @OneToOne(mappedBy ="consulta", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Dentista dentista;
    private Date data;


}
