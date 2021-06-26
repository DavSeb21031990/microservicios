package com.formacionbdi.microservicios.app.usuarios.service;

import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;

import java.util.Optional;

public interface AlumnoService {

    public Iterable<Alumno> findAll();
    public Optional<Alumno> findById(Long id);
    public Alumno save(Alumno alumno);
    public void deleteById(Long id);

}
