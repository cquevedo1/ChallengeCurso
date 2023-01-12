package com.CristianQuevedo.ChallengeQuinto.modelo.Entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "curso")
@Data
public class Curso {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "profesor")
    private String profesor;

    @Column(name = "turno")
    private String turno;

    @Column(name = "horario")
    private String horario;

    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;
}
