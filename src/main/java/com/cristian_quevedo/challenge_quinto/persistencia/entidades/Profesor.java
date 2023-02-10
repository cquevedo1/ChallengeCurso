package com.cristian_quevedo.challenge_quinto.persistencia.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "profesor")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="id_profesor")
    private String idProfesor;

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

    @OneToMany(mappedBy = "profesor")
    private List<CursosProfesor> cursos;
}
