package com.cristian_quevedo.challenge_quinto.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cursos_profesor")
@Data
@Getter
@Setter
public class CursosProfesor {
    @EmbeddedId
    private CursosProfesorPK id;
    private Integer cantidad;
    private Boolean alta;

    @ManyToOne
    @JoinColumn(name="id_curso ", insertable = false, updatable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name="id_profesor ", insertable = false, updatable = false)
    private Profesor profesor;


}
