package com.CristianQuevedo.ChallengeQuinto.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Alumno;

public interface IAlumnoRepositorio extends JpaRepository<Alumno, String>{

    // QUERY PARA BUSCAR UN ALUMNO POR SU DNI
    @Query("SELECT a FROM Alumno a WHERE a.dni = :dni")
     public Alumno findAlumnoByDni(@Param("dni") String dni);
 
     // QUERY PARA BUSCAR UN ALUMNO POR SU NOMBRE
     @Query("SELECT a FROM Alumno a WHERE a.nombre = :nombre")
     public Alumno findAlumnoByNombre(@Param("nombre") String nombre);
 
     // QUERY PARA BUSCAR UN ALUMNO POR SU APELLIDO
     @Query("SELECT a FROM Alumno a WHERE a.apellido = :apellido")
     public Alumno findAlumnoByApellido(@Param("apellido") String apellido);

}
