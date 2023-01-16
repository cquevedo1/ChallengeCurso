package com.CristianQuevedo.ChallengeQuinto.controladores;

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

import com.CristianQuevedo.ChallengeQuinto.excepciones.ExceptionCustomHandler;
import com.CristianQuevedo.ChallengeQuinto.servicios.AlumnoServicio;

@RestController
@RequestMapping(value = "alumno")
@CrossOrigin(origins = "*")
public class alumnoController {

    @Autowired
    AlumnoServicio alumnoServicio;


   /**
    * creacion de alumno
    * @param nombre
    * @param apellido
    * @param dni
    * @param fechaNacimiento
    * @param historia
    * @return
    */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearAlumno(
            String nombre,
            String apellido,
            String dni,
            String fechaNacimiento,
            String historia) {
        try {
            alumnoServicio.crearAlumno(nombre,
                    apellido,
                    dni,
                    fechaNacimiento,
                    historia);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return ExceptionCustomHandler.throwError(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

    
    /**
     * modificacion de datos del alumno
     * @param id
     * @param nombre
     * @param apellido
     * @param dni
     * @param fechaNacimiento
     * @param historia
     * @param nombreCurso
     * @param alta
     * @return
     * @throws Exception
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificarAlumno(
            @PathVariable("id") String id,
            String nombre,
            String apellido,
            String dni,
            String fechaNacimiento,
            String historia,
            String nombreCurso,
            Boolean alta)
            throws Exception {
        alumnoServicio.modificarAlumno(apellido, nombre, apellido, dni, fechaNacimiento, historia, nombreCurso, alta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   /**
    * Inscripcion a cursa
    * @param id
    * @param nombreCurso
    * @return
    * @throws Exception
    */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inscribirAlumnoEnCurso(
            @PathVariable("id") String id,
            String nombreCurso)
            throws Exception {
        alumnoServicio.inscripcionCurso(id, nombreCurso);
        return new ResponseEntity<>(HttpStatus.OK);
    }


   /**
    * Busqueda por id del alumno
    * @param id
    * @return
    * @throws Exception
    */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarAlumnoPorId(@PathVariable("id") String id) throws Exception{
        alumnoServicio.buscarAlumnoPorID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * funcion para dar de alta
     * @param id
     * @return
     */
    @GetMapping("/alta-alumno") 
    public ResponseEntity<?> altaAlumno(@PathVariable("id") String id) {
        try {
            alumnoServicio.altaAlumno(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.getMessage();
            return ExceptionCustomHandler.throwErrorNotFound(HttpStatus.NOT_FOUND, id);
        }
    }

   /**
    * funcion para dar de baja
    * @param id
    * @return
    */
    @GetMapping("/baja-alumno") 
    public ResponseEntity<?> bajaAlumno(@PathVariable("id") String id) {
        try {
            alumnoServicio.bajaAlumno(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.getMessage();
            return ExceptionCustomHandler.throwErrorNotFound(HttpStatus.NOT_FOUND, id);
        }
    }
}
