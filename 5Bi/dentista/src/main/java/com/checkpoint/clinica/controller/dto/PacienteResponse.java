package com.checkpoint.clinica.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class PacienteResponse {

    private String nome;
    private String sobrenome;
    private String rg;
    private EnderecoResponse endereco;
}
