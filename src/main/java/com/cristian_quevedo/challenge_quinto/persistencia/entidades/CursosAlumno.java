package com.cristian_quevedo.challenge_quinto.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cursos_alumno")
@Data
@Getter
@Setter
public class CursosAlumno {
    @EmbeddedId
    private CursosAlumnoPK id;
    private Integer cantidad;
    private Boolean alta;
    @ManyToOne
    @JoinColumn(name="id_curso ", insertable = false, updatable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name="id_alumno ", insertable = false, updatable = false)
    private Alumno alumno;
}
