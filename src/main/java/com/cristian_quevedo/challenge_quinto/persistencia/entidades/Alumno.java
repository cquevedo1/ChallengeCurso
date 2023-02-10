package com.cristian_quevedo.challenge_quinto.persistencia.entidades;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import org.w3c.dom.stylesheets.LinkStyle;

@Entity
@Table(name = "alumno")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="id_alumno")
    private String idAlumno;

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

    @OneToMany(mappedBy = "alumno")
    private List<CursosAlumno> cursos;

    //Se utiliza para hacer un sofDelete
    @Column(name = "alta")
    private Boolean alta;

}
