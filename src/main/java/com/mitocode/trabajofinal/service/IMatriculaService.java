package com.mitocode.trabajofinal.service;

import com.mitocode.trabajofinal.model.Matricula;

import java.util.List;
import java.util.Map;

public interface IMatriculaService extends ICRUD<Matricula, Integer>{

    Map<String, List<String>> mostrarCursoEtudiantes();

    Map<String, List<String>> mostrarCursoEtudiantesCorto();
}
