package com.CristianQuevedo.ChallengeQuinto.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Profesor;
import com.CristianQuevedo.ChallengeQuinto.repositorios.IProfesorRepositorio;

@Service
public class ProfesorServicio {
    @Autowired
    IProfesorRepositorio profesorRepositorio;

    // ****************************VALIDACION******************
    public void validar(String dni)
            throws Exception {

        Profesor p = new Profesor();
        p = null;
        p = profesorRepositorio.findProfesorByDni(dni);
        if (p != null && p.getDni() == dni) {
            throw new Exception("El DNI ya existe en la base de datos");
        } else if (dni.isEmpty() || dni == null) {
            throw new Exception("Debe ingresar datos validos para el DNI");
        }
    }

    // //****************************CREACION******************
    /**
     * Creación de un profesor
     * @param nombre
     * @param apellido
     * @param dni
     * @throws Exception
     */
    @Transactional
    public void crearProfesor(
            String nombre,
            String apellido,
            String dni)
            throws Exception {
        validar(dni);

        Profesor profesor = new Profesor();
        profesor.setApellido(apellido);
        profesor.setNombre(nombre);
        profesor.setDni(dni);
        profesor.setAlta(true);

        profesorRepositorio.save(profesor);
    }

    // ******************UPDATE***********************
    /**
     * Actualizacion de los datos de un profesor
     * @param id
     * @param nombre
     * @param apellido
     * @param dni
     * @param alta
     */
    @Transactional
    public void modificarProfesor(
            String id,
            String nombre,
            String apellido,
            String dni,
            Boolean alta) {

        Profesor profesor = buscarProfesorPorID(id);

        profesor.setNombre(nombre);
        profesor.setApellido(apellido);
        profesor.setDni(dni);
        profesor.setAlta(alta);

        profesorRepositorio.save(profesor);
    }

    // ************************BUSQUEDA O CONSULTA*******************
    /**
     * Busqueda de un profesor por dni
     * @param dni
     * @return
     */
    public Profesor buscarProfesorPorDNI(String dni) {
        Profesor existeProfesor = profesorRepositorio.findProfesorByDni(dni);
        if (existeProfesor != null) {
            return existeProfesor;
        }
        return null;
    }

    /**
     * Busqueda de un profesor por id
     * @param id
     * @return
     */
    public Profesor buscarProfesorPorID(String id) {
        Optional<Profesor> optionaProfesor = profesorRepositorio.findById(id);
        Profesor profesor = optionaProfesor.get();
        if (profesor != null) {
            return profesor;
        }
        System.out.println("No existe profesor con el ID ingresado");
        return null;
    }

    /**
     * Listado de profesores por apellido
     * @param apellido
     * @return
     */
    public List<Profesor> buscarprofesorPorApellido(String apellido) {
        List<Profesor> existeProfesor = profesorRepositorio.findProfesorByApellido(apellido);
        if (existeProfesor != null) {
            return existeProfesor;
        }
        System.out.println("No existen profesores con el apellido ingresado");
        return null;
    }

    /**
     * Listado de profesores por nombre
     * @param nombre
     * @return
     */
    public List<Profesor> buscarProfesorPorNombre(String nombre) {
        List<Profesor> existeProfesor = profesorRepositorio.findProfesorByNombre(nombre);
        if (existeProfesor != null) {
            return existeProfesor;
        }
        System.out.println("No existen profesores con el nombre ingresado");
        return null;
    }

    // ***********************BAJA*****************(SOFT DELETE)
    /**
     * Soft delete de un profesor
     * @param id
     */
    @Transactional
    public void bajaProfesor(String id) {
        Profesor profesor = buscarProfesorPorID(id);
        if (profesor != null) {
            profesor.setAlta(false);
            profesorRepositorio.save(profesor);
        }
    }

    // ***********************AlTA*****************

    /**
     * aoft alta de un profesor
     * @param id
     */
    @Transactional
    public void altaProfesor(String id) {
        Profesor profesor = buscarProfesorPorID(id);
        if (profesor != null) {
            profesor.setAlta(true);
            profesorRepositorio.save(profesor);
        }
    }
}
