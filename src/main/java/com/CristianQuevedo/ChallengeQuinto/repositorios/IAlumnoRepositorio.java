package com.CristianQuevedo.ChallengeQuinto.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Alumno;

public interface IAlumnoRepositorio extends JpaRepository<Alumno, String>{
    
     // QUERY PARA BUSCAR UN ALUMNO POR SU ID
     @Query("SELECT a FROM Alumno a WHERE a.id = :id")
     public Alumno buscarAlumnoPorId(@Param("id") String id);
 
     // QUERY PARA MOSTRAR UNA LISTA DE TODOS LOS ALUMNOS
     @Query("SELECT a FROM Alumno a  ORDER BY a.apellido ASC")
     public List<Alumno> buscarTodosLosAlumnos();
 
     // QUERY PARA BUSCAR UN ALUMNO POR SU NOMBRE
     @Query("SELECT a FROM Alumno a WHERE a.nombre = :nombre")
     public Alumno buscarAlumnoPorNombre(@Param("nombre") String nombre);
 
     // QUERY PARA BUSCAR UN ALUMNO POR SU APELLIDO
     @Query("SELECT a FROM Alumno a WHERE a.apellido = :apellido")
     public Alumno buscarAlumnoPorApellido(@Param("apellido") String apellido);
 
     // QUERY PARA BUSCAR UN ALUMNO POR ALGÚN VALOR - NOMBREO O APELLIDO
     @Query("SELECT a FROM Alumno a WHERE a.nombre LIKE :valor OR a.apellido LIKE :valor AND a.softDelete = false ORDER BY a.apellido ASC")
     public List<Alumno> BuscarAlumnoPorValor(String valor);
}
