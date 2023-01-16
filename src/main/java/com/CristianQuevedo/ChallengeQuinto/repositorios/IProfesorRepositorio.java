package com.CristianQuevedo.ChallengeQuinto.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CristianQuevedo.ChallengeQuinto.modelo.entidades.Profesor;

public interface IProfesorRepositorio extends JpaRepository<Profesor, String>{
    
    // QUERY PARA BUSCAR UN PROFESOR POR SU NOMBRE
    @Query("SELECT p FROM Profesor p WHERE p.nombre = :nombre")
    public List<Profesor> findProfesorByNombre(@Param("nombre") String nombre);

    // QUERY PARA BUSCAR UN PROFESOR POR SU APELLIDO
    @Query("SELECT p FROM Profesor p WHERE p.apellido = :apellido")
    public List<Profesor> findProfesorByApellido(@Param("apellido") String apellido);

    // QUERY PARA BUSCAR UN PROFESOR POR SU DNI
    @Query("SELECT p FROM Profesor p WHERE p.dni = :dni")
     public Profesor findProfesorByDni(@Param("dni") String dni);
}
