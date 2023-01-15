package com.CristianQuevedo.ChallengeQuinto.servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Alumno;
import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Curso;
import com.CristianQuevedo.ChallengeQuinto.modelo.Entidades.Profesor;
import com.CristianQuevedo.ChallengeQuinto.repositorios.IAlumnoRepositorio;
import com.CristianQuevedo.ChallengeQuinto.repositorios.ICursoRepositorio;
import com.CristianQuevedo.ChallengeQuinto.repositorios.IProfesorRepositorio;

@Service
public class CursoServicio {

    @Autowired
    ICursoRepositorio cursoRepositorio;

    @Autowired
    IProfesorRepositorio profesorRepositorio;

    @Autowired
    IAlumnoRepositorio alumnoRepositorio;
   

    // ****************************VALIDACION******************

    public void validarHorario(LocalDate dia, LocalTime hora) throws Exception{
        if(dia == null || hora == null){
            throw new Exception("Los datos dia y/o hora son invalidos");
        }
        if(dia.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY")){
            throw new Exception("Feriado o dia no laboral");
            }
        }

        public void validarDatos(String dniProfesor) throws Exception{

            if(profesorRepositorio.findProfesorByDni(dniProfesor) == null){
                throw new Exception("El profesor no se encuentra en la base de datos, revise los datos ingresados");
            }
    
        }

    //****************************CREACION******************
    /**
     * 
     * @param nombre
     * @param dniProfesor
     * @param turno
     * @param dia
     * @param hora
     * @throws Exception
     */
    @Transactional
    public void crearCurso(
        String nombre, 
        String dniProfesor, 
        String turno, 
        LocalDate dia ,
        LocalTime hora) throws Exception{

        validarDatos(dniProfesor);
        validarHorario(dia, hora);

        Curso curso = new Curso();

        curso.setNombre(nombre);
        curso.setProfesor(profesorRepositorio.findProfesorByDni(dniProfesor));
        curso.setTurno(turno);
        curso.setDia(dia);
        curso.setHora(hora);
        curso.setAlta(true);

        cursoRepositorio.save(curso);


    }


    // ******************UPDATE***********************
    /**
     * Modificacion de datos del curso
     * @param nombre
     * @param dniProfesor
     * @param turno
     * @param dia
     * @param hora
     * @param alta
     * @param id
     * @throws Exception
     */
    @Transactional
    public void modificarCurso(
        String nombre, 
        String dniProfesor, 
        String turno, 
        LocalDate dia, 
        LocalTime hora, 
        boolean alta, 
        String id) throws Exception{
        
        Optional<Curso> optionalCurso = cursoRepositorio.findById(id);
        Curso curso = optionalCurso.get();

        validarDatos(dniProfesor);
        validarHorario(dia, hora);

        curso.setNombre(nombre);
        curso.setProfesor(profesorRepositorio.findProfesorByDni(dniProfesor));
        curso.setTurno(turno);
        curso.setDia(dia);
        curso.setHora(hora);
        curso.setAlta(true);

        cursoRepositorio.save(curso);

    }

    /**
     * alta-baja de un curso
     * @param id
     * @param alta
     */
    //***********************ALTA / BAJA*****************(SOFT CREATE - DELETE )
    @Transactional
    public void altaCurso(String id, boolean alta){

        Optional<Curso> optionalCurso = cursoRepositorio.findById(id);
        Curso curso = optionalCurso.get();

        curso.setAlta(alta);

        cursoRepositorio.save(curso);

    }

     //************************BUSQUEDA O CONSULTA*******************
    public Curso buscarPorID(String id){
        Optional<Curso> optionalCurso = cursoRepositorio.findById(id);
        Curso curso = optionalCurso.get();
        return curso;
    }

    public Curso buscarCursoPorNombre(String nombreCurso){
        return cursoRepositorio.findCursoByNombre(nombreCurso);
    }
   
    public List<Curso> buscarPorTurno(String turno){
        return cursoRepositorio.findCursoByTurno(turno);
    }
    public List<Curso> buscarCursoPorProfesor(String dniProfesor){
        Profesor p = profesorRepositorio.findProfesorByDni(dniProfesor);
        String id = p.getId();
        return cursoRepositorio.findCursoByProfesor(id);
        
    }

    public List<Alumno> listarAlumnosCurso(String idCurso){
        return alumnoRepositorio.findAlumnoByCurso(idCurso); 
    }
    
}
