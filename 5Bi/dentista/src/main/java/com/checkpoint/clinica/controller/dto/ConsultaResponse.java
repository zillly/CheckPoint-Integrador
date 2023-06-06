package com.checkpoint.clinica.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ConsultaResponse {
    @NotNull
    private  PacienteResponse paciente;
    @NotNull
    private DentistaResponse dentista;
}
