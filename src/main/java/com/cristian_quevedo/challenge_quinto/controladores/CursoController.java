package com.cristian_quevedo.challenge_quinto.controladores;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristian_quevedo.challenge_quinto.excepciones.ExceptionCustomHandler;
import com.cristian_quevedo.challenge_quinto.servicios.CursoServicio;

@RestController
@RequestMapping(value = "/curso")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    CursoServicio cursoServicio;

    /**
     * Creacion de un curso
     * 
     * @param nombre
     * @param dniProfesor
     * @param turno
     * @param dia
     * @param hora
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearCurso(
            String nombre,
            String dniProfesor,
            String turno,
            LocalDate dia,
            final LocalTime hora) {
        try {
            cursoServicio.crearCurso(nombre, dniProfesor, turno, dia, hora);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return ExceptionCustomHandler.throwError(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    /**
     * Modificacion de los datos del curso
     * 
     * @param id
     * @param nombreCurso
     * @param dniProfesor
     * @param turno
     * @param dia
     * @param hora
     * @param alta
     * @param id
     * @return
     * @throws Exception
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificarCurso(
            @PathVariable("id") String id,
            String nombreCurso,
            String dniProfesor,
            String turno,
            LocalDate dia,
            LocalTime hora,
            boolean alta) throws Exception {
        cursoServicio.modificarCurso(nombreCurso, dniProfesor, turno, dia, hora, alta, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * funcion para dar de alta-baja
     * @param id
     * @param alta
     * @return
     */
    @GetMapping("/alta-baja") 
    public ResponseEntity<?> altaBajaCurso(@PathVariable("id") 
    String id,
     Boolean alta) {
        try {
            cursoServicio.altaCurso(id, false);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.getMessage();
            return ExceptionCustomHandler.throwErrorNotFound(HttpStatus.NOT_FOUND, id);
        }
    }

    /**
     * Lista los alumnos de un curso
     * @param idCurso
     * @return
     * @throws Exception
     */
    @GetMapping(path = "/{idCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarAlumnosPorCurso(@PathVariable("idCurso") String idCurso) throws Exception{
        cursoServicio.listarAlumnosCurso(idCurso);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
