package com.mitocode.trabajofinal.service.impl;

import com.mitocode.trabajofinal.model.Estudiante;
import com.mitocode.trabajofinal.repo.IEstudianteRepo;
import com.mitocode.trabajofinal.repo.IGenericRepo;
import com.mitocode.trabajofinal.service.IEstudianteService;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, Integer> implements IEstudianteService {

    private final IEstudianteRepo repo;
    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Estudiante> ordenarEdadDesc() {

        List<Estudiante> listaOrdenada = repo.findAll().stream()
                .sorted(Comparator.comparingInt(Estudiante::getEdad).reversed())
                .toList();

        return listaOrdenada;
    }
}
