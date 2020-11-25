/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.POJO.EstudiantePOJO;
import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.entity.Estudiante;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import co.edu.unicundi.interfaces.ILogicaEstudiante;
import co.edu.unicundi.repo.IDocenteRepo;
import co.edu.unicundi.repo.IEstudianteRepo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author YEISON
 */
@Stateless
public class LogicaEstudiante implements ILogicaEstudiante {

    @EJB
    private IEstudianteRepo repo;

    @EJB
    private IDocenteRepo repoDocente;

    @Override
    public void registrar(Estudiante estudiante) throws IdRequiredException, ObjectNotFoundException,RegisteredObjectException {
        try {
            Estudiante validar = repo.obtenerNombreApellido(estudiante.getNombre(), estudiante.getApellido());

            if (validar != null) {               
                    throw new RegisteredObjectException("El estudiante ya se encuentra registrado");                
            }
            if (estudiante.getDocente() == null || estudiante.getDocente().getId() == null) {
                throw new IdRequiredException("Id requerido dentro de objeto docente");
            } else {
                Docente docente = repoDocente.obtenerPorId(estudiante.getDocente().getId());
                if (docente != null) {
                    repo.registrar(estudiante);
                } else {
                    throw new ObjectNotFoundException("El id del docente no existe");
                }
            }
        } catch (IdRequiredException ex) {
            throw new IdRequiredException(ex.getMessage());
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    @Override
    public List<EstudiantePOJO> listar() throws ListNoContentException, NoResponseBDException {
        try {
            List<Estudiante> estudiantes = new ArrayList();
            List<EstudiantePOJO> estudiantesPOJO = new ArrayList();

            estudiantes = repo.listar();
            if (estudiantes.size() > 0) {
                for (Estudiante estudiante : estudiantes) {
                    ModelMapper model = new ModelMapper();
                    EstudiantePOJO est = model.map(estudiante, EstudiantePOJO.class);
                    estudiantesPOJO.add(est);
                }

                return estudiantesPOJO;
            } else {
                throw new ListNoContentException();
            }

        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

    @Override
    public Estudiante obtenerPorId(int id) throws ObjectNotFoundException {
        try {
            Estudiante estudiante = repo.obtenerPorId(id);
            if (estudiante != null) {
                estudiante.setDocente(null);
                return estudiante;
            } else {
                throw new ObjectNotFoundException("El id ingresado no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void bloquear(int id) throws ObjectNotFoundException, NoResponseBDException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void habilitar(int id) throws ObjectNotFoundException, NoResponseBDException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) throws ObjectNotFoundException, NoResponseBDException {
        Estudiante estudianteAux = repo.obtenerPorId(id);

        if (estudianteAux == null) {
            throw new ObjectNotFoundException("Estudiante no existe.");
        }

        repo.eliminar(estudianteAux);
    }

    @Override
    public void editar(Estudiante estudiante) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException {
        try {
            if (estudiante.getId() == null) {
                throw new IdRequiredException("Id es requerido para edición");
            }

            if (estudiante.getDocente() == null || estudiante.getDocente().getId() == null) {
                throw new IdRequiredException("Id requerido dentro de objeto docente");
            } else {
                Docente docente = repoDocente.obtenerPorId(estudiante.getDocente().getId());
                if (docente == null) {
                    throw new ObjectNotFoundException("El id del docente no existe");
                }
            }

            Estudiante estudianteAux = repo.obtenerPorId(estudiante.getId());

            if (estudianteAux == null) {
                throw new ObjectNotFoundException("Estudiante no existe.");
            }

            Estudiante validar = repo.obtenerNombreApellido(estudiante.getNombre(), estudiante.getApellido());

            if (validar != null) {
                if (validar.getId() != estudiante.getId()) {
                    throw new RegisteredObjectException("El estudiante ya se encuentra registrado");
                }
            }

            estudianteAux.setNombre(estudiante.getNombre());
            estudianteAux.setApellido(estudiante.getApellido());
            estudianteAux.setDocente(estudiante.getDocente());
            repo.editar(estudianteAux);
        } catch (IdRequiredException ex) {
            throw new IdRequiredException(ex.getMessage());
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

}
