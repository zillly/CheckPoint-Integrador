package com.checkpoint.clinica.model;

import com.checkpoint.clinica.controller.dto.EnderecoResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_endereco")
@Builder
public class Endereco  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rua;
    private int numero;
    private String cidade;
    private String estado;



}
