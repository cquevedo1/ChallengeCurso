package com.CristianQuevedo.ChallengeQuinto.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Curso;

public interface ICursoRepositorio extends JpaRepository<Curso, String>{
    
     // QUERY PARA BUSCAR UN CURSO POR SU ID
     @Query("SELECT c FROM Curso c WHERE c.id = :id")
     public Curso buscarCursoPorId(@Param("id") String id);
 
     // QUERY PARA MOSTRAR UNA LISTA DE TODOS LOS CURSOS
     @Query("SELECT c FROM Curso c ORDER BY c.nombre ASC")
     public List<Curso> buscarTodosLosCursos();
 
     // QUERY PARA BUSCAR UN CURSO POR SU NOMBRE
     @Query("SELECT c FROM Curso c WHERE c.nombre = :nombre")
     public Curso buscarCursoPorNombre(@Param("nombre") String nombre);
 
     // QUERY PARA BUSCAR UN CURSO POR SU TURNO
     @Query("SELECT c FROM Curso c WHERE c.turno = :turno")
     public Curso buscarCursoPorTurno(@Param("turno") String turno);
 
     // QUERY PARA BUSCAR UN CURSO POR ALGÚN VALOR - NOMBRE O TURNO
     @Query("SELECT C FROM Curso c WHERE c.nombre LIKE :valor OR c.Turno LIKE :valor AND c.softDelete = false ORDER BY c.nombre ASC")
     public List<Curso> BuscarCursoPorValor(String valor);
}
