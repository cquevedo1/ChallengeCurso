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
import com.CristianQuevedo.ChallengeQuinto.servicios.ProfesorServicio;

@RestController
@RequestMapping(value = "profesor")
@CrossOrigin(origins = "*")
public class profesorController {

    @Autowired
    ProfesorServicio profesorServicio;

    /**
     * creacion del profesor
     * 
     * @param nombre
     * @param apellido
     * @param dni
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearProfesor(
            String nombre,
            String apellido,
            String dni) {
        try {
            profesorServicio.crearProfesor(nombre, apellido, dni);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return ExceptionCustomHandler.throwError(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

    /**
     * modificacion de datos del profesor
     * 
     * @param id
     * @param nombre
     * @param apellido
     * @param dni
     * @param alta
     * @return
     * @throws Exception
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificarProfesor(
            @PathVariable("id") String id,
            String nombre,
            String apellido,
            String dni,
            Boolean alta)
            throws Exception {
        profesorServicio.modificarProfesor(apellido, nombre, apellido, dni, alta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Busqueda por id del profesor
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarProfesorPorId(@PathVariable("id") String id) throws Exception {
        profesorServicio.buscarProfesorPorID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * funcion para dar de alta
     *
     * @param id
     * @return
     */
    @GetMapping("/alta-profesor")
    public ResponseEntity<?> altaProfesor(@PathVariable("id") String id) {
        try {
            profesorServicio.altaProfesor(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.getMessage();
            return ExceptionCustomHandler.throwErrorNotFound(HttpStatus.NOT_FOUND, id);
        }
    }

    /**
     * funcion para dar de baja
     *
     * @param id
     * @return
     */
    @GetMapping("/baja-profesor")
    public ResponseEntity<?> bajaProfesor(@PathVariable("id") String id) {
        try {
            profesorServicio.bajaProfesor(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.getMessage();
            return ExceptionCustomHandler.throwErrorNotFound(HttpStatus.NOT_FOUND, id);
        }
    }

}
