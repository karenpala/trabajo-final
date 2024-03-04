package com.mitocode.trabajofinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCurso;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 10, nullable = false)
    private String siglas;

    @Column(nullable = false)
    private boolean estado;

}
