package com.cristian_quevedo.challenge_quinto.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
public class CursosAlumnoPK implements Serializable {
    @Column(name="id_alumno")
    private String idAlumno;

    @Column(name="id_curso")
    private String idCurso;
}
