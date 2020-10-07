/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.BD.DAODocente;
import co.edu.unicundi.POJO.DocentePOJO;
import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import co.edu.unicundi.interfaces.ILogicaDocente;
import co.edu.unicundi.repo.IDocenteRepo;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Clase que permite hacer la logica de los servicios docente
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
//Le asigna las transacciones a este bean y no al conenedor con el fin de que si hay un error, este bean acabe con la transaccion
//@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class LogicaDocente implements ILogicaDocente {

    private static String ruta = "C:\\Users\\cass4\\Desktop\\UDEC\\Semestre 8\\LINEA DE PROFUNDIZACION II\\Proyectos\\ProyectoServiciosDocente\\ProyectoServiciosDocente\\ProyectoServiciosDocente-ejb\\src\\main\\java\\co\\edu\\unicundi\\logica\\docentes.txt";

    @EJB
    private IDocenteRepo repo;
    
    /**
     * Registra el docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     * @throws NoResponseBDException
     */
    @Override
    public void registrar(Docente docente)  {
       
        repo.registrar(docente);
    }

    /**
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     * @throws ListNoContentException
     * @throws NoResponseBDException
     */
    @Override
    public List<Docente> listar() throws ListNoContentException, NoResponseBDException {
        try {
            List<Docente> docentes = repo.listar();
            if (docentes.size() > 0) {
                return docentes;
            } else {
                throw new ListNoContentException();
            }
        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

    /**
     * Obtiene un docente filtrado por la cedula especificada
     *
     * @param cedula
     * @return Docente filtrado
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    @Override
    public Docente obtenerPorCedula(String cedula) throws ObjectNotFoundException, NoResponseBDException {
        try {
            Docente docente = repo.obtenerPorCedula(cedula);
            if (docente.getId() != null) {
                return docente;
            } else {
                throw new ObjectNotFoundException("La cedula ingresada no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    /**
     * Edita los datos del docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     * @throws ObjectNotFoundException
     * @throws IdRequiredException
     * @throws NoResponseBDException
     */
    @Override
    public void editar(DocentePOJO docente) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException {
        try {
            if (docente.getId() != 0) {
                DocentePOJO docenteFiltradoId = new DAODocente().obtenerPorId(docente.getId());

                if (docenteFiltradoId.getId() == docente.getId()) {

                    List<DocentePOJO> docentes = new DAODocente().obtenerPorCedulaYCorreo(docente.getCedula(), docente.getCorreo());

                    if (docentes.size() == 0) {
                        new DAODocente().editar(docente);
                    } else if (docentes.size() == 1 && docentes.get(0).getId() == docente.getId()) {
                        new DAODocente().editar(docente);
                    } else {
                        throw new RegisteredObjectException("La cedula y/o el correo del docente ya existen");
                    }
                } else {
                    throw new ObjectNotFoundException("El id del docente no existe");
                }
            } else {
                throw new IdRequiredException("Campo id requerido");
            }
        } catch (RegisteredObjectException ex) {
            throw new RegisteredObjectException(ex.getMessage());
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        } catch (IdRequiredException ex) {
            throw new IdRequiredException(ex.getMessage());
        }
    }

    /**
     * Lista los docentes con la materia especificada
     *
     * @param materia
     * @return Lista de docentes
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    @Override
    public List<DocentePOJO> obtenerDocentesMateria(String materia) throws ObjectNotFoundException, NoResponseBDException {
        try {
            List<DocentePOJO> todosDocentes = new DAODocente().listar();

            List<DocentePOJO> docentesMateria = new ArrayList();

            for (DocentePOJO docente : todosDocentes) {
                for (String materiaDocente : docente.getMaterias()) {

                    if (materiaDocente.equals(materia)) {
                        System.out.println(materiaDocente);
                        docentesMateria.add(docente);
                    }
                }
            }

            if (docentesMateria.size() > 0) {
                return docentesMateria;
            } else {
                throw new ObjectNotFoundException("La materia ingresada no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    /**
     * Elimina un docente de acuerdo al id especificado
     *
     * @param id
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    @Override
    public void eliminar(int id) throws ObjectNotFoundException, NoResponseBDException {
        try {
            DocentePOJO docente = new DAODocente().obtenerPorId(id);
            if (docente.getId() != 0) {
                new DAODocente().eliminar(id);
            } else {
                throw new ObjectNotFoundException("El id del docente no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    /**
     * Registra el docente especificado en el fichero
     *
     * @param docente
     * @throws IOException
     */
    @Override
    public void registrarFichero(DocentePOJO docente) throws IOException {
        List<DocentePOJO> docentes;
        try {
            File f = new File(ruta);
            if (!f.exists()) {
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(docente);
                oos.close();
            } else {
                docentes = listarFichero();
                FileOutputStream fos = new FileOutputStream(ruta);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (DocentePOJO docent : docentes) {
                    oos.writeObject(docent);
                }
                oos.writeObject(docente);
                oos.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogicaDocente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogicaDocente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Lista todos los docentes registrados en el fichero
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<DocentePOJO> listarFichero() throws IOException {
        ObjectInputStream ois = null;
        ArrayList<DocentePOJO> docentes = new ArrayList();

        try {
            File f = new File(ruta);
            FileInputStream fis = new FileInputStream(f);

            ois = new ObjectInputStream(fis);
            while (true) {
                docentes.add((DocentePOJO) ois.readObject());

            }
        } catch (IOException io) {
            //Fin de la lectura
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LogicaDocente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ois.close();
        }
        return docentes;

    }

}
