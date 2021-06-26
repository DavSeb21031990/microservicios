package com.formacionbdi.microservicios.app.usuarios.repository;

import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
