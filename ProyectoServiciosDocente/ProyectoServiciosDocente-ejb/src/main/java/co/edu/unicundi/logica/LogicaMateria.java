/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.POJO.MateriaPOJO;
import co.edu.unicundi.entity.Materia;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import co.edu.unicundi.interfaces.ILogicaMateria;
import co.edu.unicundi.repo.IMateriaRepo;
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
public class LogicaMateria implements ILogicaMateria {

    @EJB
    private IMateriaRepo repo;

    @Override
    public void registrar(Materia materia) throws RegisteredObjectException, IdRequiredException, NoResponseBDException {
        try {
            if (repo.obtenerPorNombre(materia.getNombre()) == null) {
                repo.registrar(materia);
            } else {
                throw new RegisteredObjectException("El nombre de la materia ya se encuentra registrada");
            }
        } catch (RegisteredObjectException ex) {
            throw new RegisteredObjectException(ex.getMessage());
        }
    }

    @Override
    public List<MateriaPOJO> listar() throws ListNoContentException, NoResponseBDException {
        try {
            List<Materia> materias = new ArrayList();
            List<MateriaPOJO> materiasPOJO = new ArrayList();

            materias = repo.listar();
            if (materias.size() > 0) {
                for (Materia materia : materias) {
                    ModelMapper model = new ModelMapper();
                    MateriaPOJO mat = model.map(materia, MateriaPOJO.class);
                    materiasPOJO.add(mat);
                }

                return materiasPOJO;
            } else {
                throw new ListNoContentException();
            }

        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

    @Override
    public Materia obtenerPorId(int id) throws ObjectNotFoundException {
        try {
            Materia materia = repo.obtenerPorId(id);
            if (materia != null) {
                return materia;
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
        Materia materiaAux = repo.obtenerPorId(id);

        if (materiaAux == null) {
            throw new ObjectNotFoundException("Materia no existe.");
        }

        repo.eliminar(materiaAux);
    }

    @Override
    public void editar(Materia materia) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException {
        try {
            if (materia.getId() == null) {
                throw new IdRequiredException("Id es requerido para edición");
            }

            if (repo.obtenerPorNombre(materia.getNombre()) != null) {
                throw new RegisteredObjectException("El nombre de la materia ya se encuentra registrada");
            }

            Materia materiaAux = repo.obtenerPorId(materia.getId());

            if (materiaAux == null) {
                throw new ObjectNotFoundException("El id de la materia no existe.");
            }

            materiaAux.setNombre(materia.getNombre());
            repo.editar(materiaAux);
        } catch (IdRequiredException ex) {
            throw new IdRequiredException(ex.getMessage());
        } catch (RegisteredObjectException ex) {
            throw new RegisteredObjectException(ex.getMessage());
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    @Override
    public List<MateriaPOJO> listarNoAsociadas(Integer idDocente) throws ListNoContentException, NoResponseBDException {
        try {
            List<Materia> materias = new ArrayList();
            List<MateriaPOJO> materiasPOJO = new ArrayList();

            materias = repo.listarNoAsociadas(idDocente);
            if (materias.size() > 0) {
                for (Materia materia : materias) {
                    ModelMapper model = new ModelMapper();
                    MateriaPOJO mat = model.map(materia, MateriaPOJO.class);
                    materiasPOJO.add(mat);
                }

                return materiasPOJO;
            } else {
                throw new ListNoContentException();
            }

        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

}
