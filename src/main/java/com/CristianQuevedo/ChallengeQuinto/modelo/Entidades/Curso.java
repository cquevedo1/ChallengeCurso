package com.CristianQuevedo.ChallengeQuinto.modelo.Entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "curso")
@Data
public class Curso {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profesor_id")
    private Profesor profesor;

    @Column(name = "turno")
    private String turno;

    @Column(name = "horario")
    private String horario;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    //Se utiliza para hacer un sofDelete
    @Column(name = "alta")
    private Boolean alta;
}
