package com.mitocode.trabajofinal.service.impl;

import com.mitocode.trabajofinal.model.Curso;
import com.mitocode.trabajofinal.repo.ICursoRepo;
import com.mitocode.trabajofinal.repo.IGenericRepo;
import com.mitocode.trabajofinal.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepo repo;
    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }
}
