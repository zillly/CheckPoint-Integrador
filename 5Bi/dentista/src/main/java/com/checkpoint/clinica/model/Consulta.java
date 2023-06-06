package com.checkpoint.clinica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "tb_consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Dentista dentista;
    private LocalDate data = LocalDate.now();


}
