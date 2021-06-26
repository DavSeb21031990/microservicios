package com.formacionbdi.microservicios.app.usuarios.controller;

import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;
import com.formacionbdi.microservicios.app.usuarios.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    //Si no se define una ruta, toma por defecto la ruta raiz
    @GetMapping
    //Se usa el generico para enviar cualquier tipo de dato
    public ResponseEntity<?> listar(){
        //Se envia al cuerpo de la respuesta la lista
        return ResponseEntity.ok().body(alumnoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<Alumno> alumnoOptional = alumnoService.findById(id);
        if(alumnoOptional.isEmpty()){
            //Retorna un body vacio
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumnoOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Alumno alumno){
        Alumno alumnoDb = alumnoService.save(alumno);
        //Retorna en el body con status creado
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Alumno alumno,
                                    @PathVariable Long id){
        Optional<Alumno> alumnoOptional = alumnoService.findById(id);
        if(alumnoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Alumno alumnoDb = alumnoOptional.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoDb));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        alumnoService.deleteById(id);
        //Retorna un responseBody sin contenido
        return ResponseEntity.noContent().build();
    }

}
