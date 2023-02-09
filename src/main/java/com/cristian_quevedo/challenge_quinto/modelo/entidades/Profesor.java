package com.cristian_quevedo.challenge_quinto.modelo.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "profesor")
@Data
public class Profesor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

     //Se agrega la columna dni que no está solicitada, consultar si es correcto
     @Column(name = "dni")
     private String dni;

    //Se utiliza para hacer un sofDelete
    @Column(name = "alta")
    private Boolean alta;
}
