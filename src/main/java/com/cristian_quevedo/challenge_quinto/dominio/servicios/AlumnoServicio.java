package com.cristian_quevedo.challenge_quinto.dominio.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.cristian_quevedo.challenge_quinto.dominio.repositorios.IAlumnoRepositorio;
import com.cristian_quevedo.challenge_quinto.dominio.repositorios.ICursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristian_quevedo.challenge_quinto.persistencia.entidades.Alumno;

@Service
public class AlumnoServicio {

    @Autowired
    IAlumnoRepositorio alumnoRepositorio;
    @Autowired
    ICursoRepositorio cursoRepositorio;

    // ****************************VALIDACION******************
    /**
     * 
     * @param dni
     * @throws Exception
     */
    public void validar(String dni)
            throws Exception {

        Alumno a = new Alumno();
        a = alumnoRepositorio.findAlumnoByDni(dni);
        if (a != null && a.getDni() == dni) {
            throw new Exception("El DNI ya existe en la base de datos");
        } else if (dni.isEmpty() || dni == null) {
            throw new Exception("Debe ingresar datos validos para el DNI");
        }
    }

    // ****************************CREACION******************
    /**
     * 
     */
    @Transactional
    public void crearAlumno(
            String nombre,
            String apellido,
            String dni,
            String fechaNacimiento,
            String historia)
            throws Exception {
        validar(dni);

        Alumno alumno = new Alumno();
        alumno.setApellido(apellido);
        alumno.setNombre(nombre);
        alumno.setDni(dni);
        // Parseo de String de formulario a localDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        alumno.setFechaNacimiento(LocalDate.parse(fechaNacimiento, formatter));
        // Calculo la edad a partir de la fecha de nacimiento
        alumno.setEdad(Period.between(alumno.getFechaNacimiento(),
                LocalDate.now()).getYears());
        alumno.setHistoria(historia);
        alumno.setAlta(true);

        alumnoRepositorio.save(alumno);
    }

    @Transactional
    public void inscripcionCurso(
            String dniAlumno,
            String nombreCurso) throws Exception {
        validar(dniAlumno);
        Alumno alumno = alumnoRepositorio.findAlumnoByDni(dniAlumno);
        alumno.setCurso(cursoRepositorio.findCursoByNombre(nombreCurso));

        alumnoRepositorio.save(alumno);
    }

    // ******************UPDATE***********************
    @Transactional
    public void modificarAlumno(
            String id,
            String nombre,
            String apellido,
            String dni,
            String fechaNacimiento,
            String historia,
            String nombreCurso,
            Boolean alta) {

        Alumno alumno = buscarAlumnoPorID(id);
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setDni(dni);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        alumno.setFechaNacimiento(LocalDate.parse(fechaNacimiento, formatter));
        alumno.setHistoria(historia);
        alumno.setAlta(alta);

        alumnoRepositorio.save(alumno);
    }

    // ************************BUSQUEDA O CONSULTA*******************
    /**
     * Buscar por dni
     * @param dni
     * @return
     */
    public Alumno buscarAlumnoPorDNI(String dni) {
        Alumno existeAlumno = alumnoRepositorio.findAlumnoByDni(dni);
        if (existeAlumno != null) {
            return existeAlumno;
        }
        return null;
    }
    /**
     * Buscar por id
     * @param id
     * @return
     */
    public Alumno buscarAlumnoPorID(String id) {
        Optional<Alumno> optionaAlumno = alumnoRepositorio.findById(id);
        Alumno alumno = optionaAlumno.get();
        if (alumno != null) {
            return alumno;
        }
        System.out.println("No existe alumno con el ID ingresado");
        return null;
    }
    /**
     * Buscar por apellido
     * @param apellido
     * @return
     */
    public List<Alumno> buscarAlumnoPorApellido(String apellido) {
        List<Alumno> existeAlumno = alumnoRepositorio.findAlumnoByApellido(apellido);
        if (existeAlumno != null) {
            return existeAlumno;
        }
        System.out.println("No existen alumnos con el apellido ingresado");
        return null;
    }

    /**
     * Buscar por nombre
     * @param nombre
     * @return
     */
    public List<Alumno> buscarAlumnoPorNombre(String nombre) {
        List<Alumno> existeAlumno = alumnoRepositorio.findAlumnoByNombre(nombre);
        if (existeAlumno != null) {
            return existeAlumno;
        }
        System.out.println("No existen alumnos con el nombre ingresado");
        return null;
    }

    // ***********************BAJA*****************(SOFT DELETE)
    /**
     * Soft delete
     * @param id
     */
    @Transactional
    public void bajaAlumno(String id) {
        Alumno alumno = buscarAlumnoPorID(id);
        if (alumno != null) {
            alumno.setAlta(false);
            alumnoRepositorio.save(alumno);
        }
    }

    // ***********************AlTA*****************
    /**
     * Soft alta
     * @param id
     */
    @Transactional
    public void altaAlumno(String id) {
        Alumno alumno = buscarAlumnoPorID(id);
        if (alumno != null) {
            alumno.setAlta(true);
            alumnoRepositorio.save(alumno);
        }
    }
}
