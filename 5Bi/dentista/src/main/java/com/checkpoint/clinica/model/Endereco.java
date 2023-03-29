package com.checkpoint.clinica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rua;
    private int numero;
    private String cidade;
    private String estado;
    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;



}
