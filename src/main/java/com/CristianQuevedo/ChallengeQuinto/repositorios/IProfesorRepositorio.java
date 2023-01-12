package com.CristianQuevedo.ChallengeQuinto.repositorios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Profesor;

public interface IProfesorRepositorio extends JpaRepository<Profesor, String>{
    
    // QUERY PARA BUSCAR UN Profesor POR SU ID
    @Query("SELECT p FROM Profesor p WHERE p.id = :id")
    public Profesor buscarProfesoPorId(@Param("id") String id);

    // QUERY PARA MOSTRAR UNA LISTA DE TODOS LOS Profesores
    @Query("SELECT p FROM Profesor p  ORDER BY p.apellido ASC")
    public List<Profesor> buscarTodosLosProfesores();

    // QUERY PARA BUSCAR UN Profesor POR SU NOMBRE
    @Query("SELECT p FROM Profesor p WHERE p.nombre = :nombre")
    public Profesor buscarProfesorPorNombre(@Param("nombre") String nombre);

    // QUERY PARA BUSCAR UN Profesor POR SU APELLIDO
    @Query("SELECT p FROM Profesor p WHERE p.apellido = :apellido")
    public Profesor buscarProfesorPorApellido(@Param("apellido") String apellido);

    // QUERY PARA BUSCAR UN Profesor POR ALGÚN VALOR - NOMBREO O APELLIDO
    @Query("SELECT p FROM Profesor p WHERE p.nombre LIKE :valor OR p.apellido LIKE :valor AND p.softDelete = false ORDER BY p.apellido ASC")
    public List<Profesor> BuscarProfesorPorValor(String valor);
}
