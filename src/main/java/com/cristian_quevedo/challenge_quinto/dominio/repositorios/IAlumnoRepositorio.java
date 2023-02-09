package com.cristian_quevedo.challenge_quinto.dominio.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cristian_quevedo.challenge_quinto.persistencia.entidades.Alumno;

public interface IAlumnoRepositorio extends JpaRepository<Alumno, String>{

    // QUERY PARA BUSCAR UN ALUMNO POR SU DNI
    @Query("SELECT a FROM Alumno a WHERE a.dni = :dni")
     public Alumno findAlumnoByDni(@Param("dni") String dni);
 
     // QUERY PARA BUSCAR UN ALUMNO POR SU NOMBRE
     @Query("SELECT a FROM Alumno a WHERE a.nombre = :nombre")
     public List<Alumno> findAlumnoByNombre(@Param("nombre") String nombre);
 
     // QUERY PARA BUSCAR UN ALUMNO POR SU APELLIDO
     @Query("SELECT a FROM Alumno a WHERE a.apellido = :apellido")
     public List<Alumno> findAlumnoByApellido(@Param("apellido") String apellido);

     // QUERY PARA BUSCAR ALUMNOS POR CURSO
     @Query("SELECT a FROM Alumno a WHERE a.curso.id = :id")
     public List<Alumno> findAlumnoByCurso(@Param("id") String id);

}
