package com.CristianQuevedo.ChallengeQuinto.modelo.Entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "alumno")
@Data
public class Alumno {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "fecha_Nacimiento")
    private Date fechaNacimiento;

    @Column(name = "historia")
    private String historia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id", nullable=false)
    private List<Curso> cursos;
}
