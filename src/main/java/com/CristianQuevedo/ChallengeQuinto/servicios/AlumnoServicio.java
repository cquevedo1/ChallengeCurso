package com.CristianQuevedo.ChallengeQuinto.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Alumno;
import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Curso;
import com.CristianQuevedo.ChallengeQuinto.repositorios.IAlumnoRepositorio;

@Service
public class AlumnoServicio {

    @Autowired
    IAlumnoRepositorio alumnoRepositorio;

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

    // //****************************CREACION******************
    @Transactional
    public void crearAlumno(String nombre,
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

    // ******************UPDATE***********************
    @Transactional
    public void modificarAlumno(String id,
            String nombre,
            String apellido,
            String dni,
            String fechaNacimiento,
            String historia,
            Curso curso,
            Boolean alta) {
        Optional<Alumno> optionaAlumno = alumnoRepositorio.findById(id);
        Alumno alumno = optionaAlumno.get();

        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setDni(dni);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        alumno.setFechaNacimiento(LocalDate.parse(fechaNacimiento, formatter));
        alumno.setHistoria(historia);
        alumno.setCurso(curso);
        alumno.setAlta(alta);

        alumnoRepositorio.save(alumno);
    }
}
