package com.mitocode.trabajofinal.service;

import com.mitocode.trabajofinal.model.Estudiante;

import java.util.List;

public interface IEstudianteService extends ICRUD<Estudiante, Integer>{

    List<Estudiante> ordenarEdadDesc();

}
