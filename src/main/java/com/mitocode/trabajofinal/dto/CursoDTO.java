package com.mitocode.trabajofinal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {

    private Integer idCurso;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombre;

    @NotNull
    @Size(min = 3, max = 10)
    private String siglas;

    @NotNull
    private boolean estado;
}
