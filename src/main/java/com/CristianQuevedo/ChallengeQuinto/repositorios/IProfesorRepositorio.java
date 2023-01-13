package com.CristianQuevedo.ChallengeQuinto.repositorios;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Profesor;

public interface IProfesorRepositorio extends JpaRepository<Profesor, String>{
    
    // QUERY PARA BUSCAR UN Profesor POR SU NOMBRE
    @Query("SELECT p FROM Profesor p WHERE p.nombre = :nombre")
    public Profesor findProfesorByNombre(@Param("nombre") String nombre);

    // QUERY PARA BUSCAR UN Profesor POR SU APELLIDO
    @Query("SELECT p FROM Profesor p WHERE p.apellido = :apellido")
    public Profesor findProfesorByApellido(@Param("apellido") String apellido);
}
