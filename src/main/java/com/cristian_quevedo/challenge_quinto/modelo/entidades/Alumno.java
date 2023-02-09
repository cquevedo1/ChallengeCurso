package com.cristian_quevedo.challenge_quinto.modelo.entidades;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "alumno")
@Data
public class Alumno {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "nombre")
    private String nombre;

    //Se agrega la columna apellido que no está solicitada, consultar si es correcto
    @Column(name = "apellido")
    private String apellido;

    //Se agrega la columna dni que no está solicitada, consultar si es correcto
    @Column(name = "dni")
    private String dni;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "fecha_Nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "historia")
    private String historia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id")
    private Curso curso;

    //Se utiliza para hacer un sofDelete
    @Column(name = "alta")
    private Boolean alta;

}
