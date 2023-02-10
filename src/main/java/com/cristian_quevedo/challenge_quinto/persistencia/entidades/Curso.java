package com.cristian_quevedo.challenge_quinto.persistencia.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="id_curso")
    private String idCurso;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profesor_id")
    private Profesor profesor;

    @Column(name = "turno")
    private String turno;

    //El horario de cursado se especifica mediante día y hora
    @Column(name = "dia")
    private LocalDate dia;

    @Column(name = "hora")
    private LocalTime hora;

    //Se utiliza para hacer un sofDelete
    @Column(name = "alta")
    private Boolean alta;
}
