package com.mitocode.trabajofinal.service.impl;


import com.mitocode.trabajofinal.model.DetalleMatricula;
import com.mitocode.trabajofinal.model.Estudiante;
import com.mitocode.trabajofinal.model.Matricula;
import com.mitocode.trabajofinal.repo.IGenericRepo;
import com.mitocode.trabajofinal.repo.IMatriculaRepo;
import com.mitocode.trabajofinal.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends CRUDImpl<Matricula, Integer> implements IMatriculaService {

    private final IMatriculaRepo repo;
    @Override
    protected IGenericRepo<Matricula, Integer> getRepo() {
        return repo;
    }

  /*  @Override
    public void mostrarCursoEtudiantes() {

        Stream<Matricula> matriculaStream = repo.findAll().stream();
        Stream<List<DetalleMatricula>> lsStream = matriculaStream.map(Matricula::getDetalleMatricula);

        //[ [det1,det2],  [det3,det4] ,    [det5,det6] ..... ]
        //[det1, det2, det3, det4, det5, det6 ...]
        Stream<DetalleMatricula> streamDetail = lsStream.flatMap(Collection::stream); //list -> list.stream()

        Map<String, List<DetalleMatricula>> byProduct = streamDetail
                .collect(groupingBy(d -> d.getCurso().getNombre(), toList()));

        Map<String, List<String>> mapaResp = new HashMap<>();
        // Imprimir la relación de cursos y estudiantes
        byProduct.forEach(
                (curso, estudiantes) -> {
                    System.out.println("Curso: " + curso);
                    System.out.println("Estudiantes:");
                    List<String> listaNombres = new ArrayList<>();
                    estudiantes.forEach(
                            estudiante -> {
                                System.out.println("- " + estudiante.getMatricula().getEstudiante().getNombres())
                                listaNombres.add(estudiante.getMatricula().getEstudiante().getNombres());
                            }
                    );

                    mapaResp.put(curso, listaNombres);
        });


    }*/

    @Override
    public Map<String, List<String>> mostrarCursoEtudiantes() {

        Stream<Matricula> matriculaStream = repo.findAll().stream();
        Stream<List<DetalleMatricula>> lsStream = matriculaStream.map(Matricula::getDetalleMatricula);
        Stream<DetalleMatricula> streamDetail = lsStream.flatMap(Collection::stream); //list -> list.stream()

        Map<String, List<DetalleMatricula>> listCursoMatricula = streamDetail
                .collect(groupingBy(d -> d.getCurso().getNombre(), toList()));

        Map<String, List<String>> mapaResp = new HashMap<>();
        // Imprimir la relación de cursos y estudiantes
        listCursoMatricula.forEach(
                (curso, estudiantes) -> {
                    System.out.println("Curso: " + curso);
                    System.out.println("Estudiantes:");
                    List<String> listaNombres = new ArrayList<>();
                    estudiantes.forEach(
                            estudiante -> {
                                System.out.println("- " + estudiante.getMatricula().getEstudiante().getNombres());
                                listaNombres.add(estudiante.getMatricula().getEstudiante().getNombres()+" "+estudiante.getMatricula().getEstudiante().getApellidos());
                            }
                    );

                    mapaResp.put(curso, listaNombres);
                });
        return mapaResp;
    }

    @Override
    public Map<String, List<String>> mostrarCursoEtudiantesCorto() {
        List<Matricula> matriculas = repo.findAll();

        Map<String, List<String>> cursoEstudiantesMap = matriculas.stream()
                .flatMap(matricula -> matricula.getDetalleMatricula().stream()
                        .map(detalle -> Map.entry(detalle.getCurso().getNombre(), matricula.getEstudiante().getNombres())))
                .collect(Collectors.groupingBy(m->m.getKey(),
                        Collectors.mapping(s->s.getValue(), Collectors.toList())));

        return cursoEstudiantesMap;
    }
}
