package com.CristianQuevedo.ChallengeQuinto.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Alumno;
import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Curso;
import com.CristianQuevedo.ChallengeQuinto.repositorios.IAlumnoRepositorio;
import com.CristianQuevedo.ChallengeQuinto.repositorios.ICursoRepositorio;

@Service
public class AlumnoServicio {

    @Autowired
    IAlumnoRepositorio alumnoRepositorio;
    @Autowired
    ICursoRepositorio cursoRepositorio;

    // ****************************VALIDACION******************
    public void validar(String dni)
            throws Exception {

        Alumno a = new Alumno();
        a = null;
        a = alumnoRepositorio.findAlumnoByDni(dni);
        if (a != null && a.getDni() == dni) {
            throw new Exception("El DNI ya existe en la base de datos");
        } else if (dni.isEmpty() || dni == null) {
            throw new Exception("Debe ingresar datos validos para el DNI");
        }
    }

    // ****************************CREACION******************
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
    public void inscripcionCurso(String dniAlumno, String nombreCurso) throws Exception {
        validar(dniAlumno);
        Alumno alumno = alumnoRepositorio.findAlumnoByDni(dniAlumno);
        alumno.setCurso(cursoRepositorio.findCursoByNombre(nombreCurso));

        alumnoRepositorio.save(alumno);
    }

    // ******************UPDATE***********************
    @Transactional
    public void modificarAlumno(String id,
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
    public Alumno buscarAlumnoPorDNI(String dni) {
        Alumno existeAlumno = alumnoRepositorio.findAlumnoByDni(dni);
        if (existeAlumno != null) {
            return existeAlumno;
        }
        return null;
    }

    public Alumno buscarAlumnoPorID(String id) {
        Optional<Alumno> optionaAlumno = alumnoRepositorio.findById(id);
        Alumno alumno = optionaAlumno.get();
        if (alumno != null) {
            return alumno;
        }
        System.out.println("No existe alumno con el ID ingresado");
        return null;
    }

    public List<Alumno> buscarAlumnoPorApellido(String apellido) {
        List<Alumno> existeAlumno = alumnoRepositorio.findAlumnoByApellido(apellido);
        if (existeAlumno != null) {
            return existeAlumno;
        }
        System.out.println("No existen alumnos con el apellido ingresado");
        return null;
    }

    public List<Alumno> buscarAlumnoPorNombre(String nombre) {
        List<Alumno> existeAlumno = alumnoRepositorio.findAlumnoByNombre(nombre);
        if (existeAlumno != null) {
            return existeAlumno;
        }
        System.out.println("No existen alumnos con el nombre ingresado");
        return null;
    }

    // ***********************BAJA*****************(SOFT DELETE)
    @Transactional
    public void bajaAlumno(String id) {
        Alumno alumno = buscarAlumnoPorID(id);
        if (alumno != null) {
            alumno.setAlta(false);
            alumnoRepositorio.save(alumno);
        }
    }

    // ***********************AlTA*****************

    @Transactional
    public void altaAlumno(String id) {
        Alumno alumno = buscarAlumnoPorID(id);
        if (alumno != null) {
            alumno.setAlta(true);
            alumnoRepositorio.save(alumno);
        }
    }
}
