package com.CristianQuevedo.ChallengeQuinto.repositorios;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Curso;

public interface ICursoRepositorio extends JpaRepository<Curso, String>{

  
     // QUERY PARA BUSCAR UN CURSO POR SU NOMBRE
     @Query("SELECT c FROM Curso c WHERE c.nombre = :nombre")
     public Curso findCursoByNombre(@Param("nombre") String nombre);
 
     // QUERY PARA BUSCAR UN CURSO POR SU TURNO
     @Query("SELECT c FROM Curso c WHERE c.turno = :turno")
     public Curso findCursoByTurno(@Param("turno") String turno);
}
