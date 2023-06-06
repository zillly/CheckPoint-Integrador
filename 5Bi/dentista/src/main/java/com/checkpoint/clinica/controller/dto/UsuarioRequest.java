package com.checkpoint.clinica.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UsuarioRequest {

    private String nome;
    private String email;
    private String senha;
    private  String nivelAcesso;
}
