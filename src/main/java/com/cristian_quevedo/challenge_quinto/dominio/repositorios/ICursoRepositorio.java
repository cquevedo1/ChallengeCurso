package com.cristian_quevedo.challenge_quinto.dominio.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cristian_quevedo.challenge_quinto.persistencia.entidades.Curso;

public interface ICursoRepositorio extends JpaRepository<Curso, String>{

  
     // QUERY PARA BUSCAR UN CURSO POR SU NOMBRE
     @Query("SELECT c FROM Curso c WHERE c.nombre = :nombre")
     public Curso findCursoByNombre(@Param("nombre") String nombre);
 
     // QUERY PARA BUSCAR UN CURSO POR SU TURNO
     @Query("SELECT c FROM Curso c WHERE c.turno = :turno")
     public List<Curso> findCursoByTurno(@Param("turno") String turno);

     // QUERY PARA BUSCAR CURSOS POR PROFESOR
     @Query("SELECT c FROM Curso c WHERE c.profesor.id = :id ORDER BY t.dia ASC")
     public List<Curso> findCursoByProfesor(@Param("id") String id);
}
