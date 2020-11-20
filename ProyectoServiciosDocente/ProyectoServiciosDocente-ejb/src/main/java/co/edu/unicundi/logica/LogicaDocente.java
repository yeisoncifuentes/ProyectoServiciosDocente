/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.logica;

import co.edu.unicundi.POJO.DocenteMateriaPOJO;
import co.edu.unicundi.POJO.DocentePOJO;
import co.edu.unicundi.POJO.GenericoPOJO;
import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.entity.DocenteMateria;
import co.edu.unicundi.entity.Estudiante;
import co.edu.unicundi.entity.Materia;
import co.edu.unicundi.exception.IdRequiredException;
import co.edu.unicundi.exception.ListNoContentException;
import co.edu.unicundi.exception.NoResponseBDException;
import co.edu.unicundi.exception.ObjectNotFoundException;
import co.edu.unicundi.exception.RegisteredObjectException;
import co.edu.unicundi.interfaces.ILogicaDocente;
import co.edu.unicundi.repo.IDocenteMateriaRepo;
import co.edu.unicundi.repo.IDocenteRepo;
import co.edu.unicundi.repo.IMateriaRepo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

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

    @EJB
    private IDocenteMateriaRepo repoDocenteMateria;

    @EJB
    private IMateriaRepo repoMateria;

    /**
     * Registra el docente especificado
     *
     * @param docente
     * @throws RegisteredObjectException
     * @throws NoResponseBDException
     */
    @Override
    public void registrar(Docente docente) throws RegisteredObjectException, NoResponseBDException {
        try {

            if (docente.getDireccion() == null || docente.getDireccion().getBarrio() == null || docente.getDireccion().getDireccion() == null) {
                throw new IdRequiredException("Direccion y barrio requerido dentro de objeto direccion");
            }

            Docente validarCedula = repo.obtenerPorCedula(docente.getCedula());
            Docente validarCorreo = repo.obtenerPorCorreo(docente.getCorreo());

            if (validarCedula == null && validarCorreo == null) {
                if (docente.getEstudiantes() != null) {
                    for (Estudiante estudiante : docente.getEstudiantes()) {
                        estudiante.setDocente(docente);
                    }
                }
                if (docente.getDireccion() != null) {
                    docente.getDireccion().setDocente(docente);
                }
                docente.setEstado(true);
                repo.registrar(docente);
            } else if (validarCedula == null && validarCorreo != null) {
                throw new RegisteredObjectException("El correo del docente ya existe");
            } else if (validarCedula != null && validarCorreo == null) {
                throw new RegisteredObjectException("La cedula del docente ya existe");
            } else {
                throw new RegisteredObjectException("La cedula y el correo del docente ya existen");
            }
        } catch (RegisteredObjectException ex) {
            throw new RegisteredObjectException(ex.getMessage());
        } catch (IdRequiredException ex) {
            throw new RegisteredObjectException(ex.getMessage());
        }
    }

    /**
     * Lista todos los docentes registrados
     *
     * @return Lista de docentes
     * @throws ListNoContentException
     * @throws NoResponseBDException
     */
    @Override
    public List<DocentePOJO> listar(boolean filtro) throws ListNoContentException, NoResponseBDException {
        try {
            List<Docente> docentes = new ArrayList();
            List<DocentePOJO> docentesPojo = new ArrayList();

            docentes = repo.listar();
            if (docentes.size() > 0) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                for (Docente docente : docentes) {
                    ModelMapper model = new ModelMapper();
                    DocentePOJO doc = model.map(docente, DocentePOJO.class);
                    
                    String fechaTexto = formatter.format(doc.getFechaNacimiento());
                    doc.setFechaNacimientoFormato(fechaTexto);
                    docentesPojo.add(doc);
                }

                if (!filtro) {
                    for (DocentePOJO doc : docentesPojo) {
                        doc.setEstudiantes(null);
                    }
                }

                return docentesPojo;
            } else {
                throw new ListNoContentException();
            }

        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

    /**
     * Lista todos los docentes registrados sin estudiantes
     *
     * @return Lista de docentes
     * @throws ListNoContentException
     * @throws NoResponseBDException
     */
    @Override
    public List<DocentePOJO> listar2() throws ListNoContentException, NoResponseBDException {
        try {
            List<Docente> docentes = new ArrayList();
            List<DocentePOJO> docentesPojo = new ArrayList();

            docentes = repo.listar2();
            if (docentes.size() > 0) {
                for (int i = 0; i < docentes.size(); i++) {
                    ModelMapper model = new ModelMapper();
                    Object[] doc = model.map(docentes.get(i), Object[].class);
                    docentesPojo.add(new DocentePOJO((Integer) doc[0], (String) doc[1], (String) doc[2], (String) doc[3], (String) doc[4], (Date) doc[5], (Boolean) doc[6]));
                }

                return docentesPojo;
            } else {
                throw new ListNoContentException();
            }

        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

    @Override
    public GenericoPOJO listarPaginado(int cantidadDatos, int paginaActual) throws ListNoContentException, NoResponseBDException {
        try {

            List<Docente> docentes = new ArrayList();

            GenericoPOJO<DocentePOJO> docentesPojo = new GenericoPOJO<DocentePOJO>();
            docentesPojo.setPaginado(this.listar2().size());

            docentes = repo.listarPaginado(cantidadDatos, paginaActual);
            if (docentes.size() > 0) {
                for (int i = 0; i < docentes.size(); i++) {
                    ModelMapper model = new ModelMapper();
                    Object[] doc = model.map(docentes.get(i), Object[].class);
                    docentesPojo.add(new DocentePOJO((Integer) doc[0], (String) doc[1], (String) doc[2], (String) doc[3], (String) doc[4], (Date) doc[5], (Boolean) doc[6]));
                }

                return docentesPojo;
            } else {
                throw new ListNoContentException();
            }

        } catch (ListNoContentException ex) {
            throw new ListNoContentException();
        }
    }

    /**
     * Lista todos los docentes registrados
     *
     * @param filtro
     * @return Lista de docentes
     * @throws ListNoContentException
     * @throws NoResponseBDException
     */
    @Override
    public List<Docente> listar3(boolean filtro) throws ListNoContentException, NoResponseBDException {
        try {
            List<Docente> docentes = new ArrayList();

            docentes = repo.listar3();
            if (docentes.size() > 0) {
                if (!filtro) {
                    for (Docente doc : docentes) {
                        doc.setEstudiantes(null);
                    }
                }
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
            if (docente != null) {
                docente.setEstudiantes(null);
                return docente;
            } else {
                throw new ObjectNotFoundException("La cedula ingresada no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    /**
     * Obtiene un docente filtrado por el id especificado
     *
     * @param id
     * @return Docente filtrado
     * @throws ObjectNotFoundException
     */
    @Override
    public Docente obtenerPorId(int id) throws ObjectNotFoundException {
        try {
            Docente docente = repo.obtenerPorId(id);
            if (docente != null) {
                docente.setEstudiantes(null);
                return docente;
            } else {
                throw new ObjectNotFoundException("El id ingresado no existe");
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
    public void editar(Docente docente) throws RegisteredObjectException, ObjectNotFoundException, IdRequiredException, NoResponseBDException {
        try {
            if (docente.getDireccion() == null || docente.getDireccion().getBarrio() == null || docente.getDireccion().getDireccion() == null) {
                throw new IdRequiredException("Direccion y barrio requerido dentro de objeto direccion");
            }
            if (docente.getId() != null) {
                Docente docenteAux = repo.obtenerPorId(docente.getId());

                if (docenteAux != null) {
                    Docente validarCedula = repo.obtenerPorCedula(docenteAux.getCedula());
                    Docente validarCorreo = repo.obtenerPorCorreo(docenteAux.getCorreo());

                    docenteAux.setCedula(docente.getCedula());
                    docenteAux.setNombre(docente.getNombre());
                    docenteAux.setApellido(docente.getApellido());
                    docenteAux.setCorreo(docente.getCorreo());
                    docenteAux.setFechaNacimiento(docente.getFechaNacimiento());

                    if (docente.getDireccion() != null) {
                        docenteAux.getDireccion().setBarrio(docente.getDireccion().getBarrio());
                        docenteAux.getDireccion().setDireccion(docente.getDireccion().getDireccion());
                    }

                    if (validarCedula == null) {
                        if (validarCorreo == null || validarCorreo.getId().equals(docente.getId())) {
                            repo.editar(docenteAux);
                        } else {
                            throw new RegisteredObjectException("El correo del docente ya existe");
                        }
                    } else if (validarCedula.getId().equals(docente.getId())) {
                        if (validarCorreo == null || validarCorreo.getId().equals(docente.getId())) {
                            repo.editar(docenteAux);
                        } else {
                            throw new RegisteredObjectException("El correo del docente ya existe");
                        }
                    } else {
                        if (validarCorreo == null || validarCorreo.getId().equals(docente.getId())) {
                            throw new RegisteredObjectException("La cedula del docente ya existe");
                        } else {
                            throw new RegisteredObjectException("La cedula y el correo del docente ya existen");
                        }
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

    @Override
    public void bloquear(int id) throws ObjectNotFoundException, NoResponseBDException {
        Docente docenteAux = repo.obtenerPorId(id);
        if (docenteAux != null) {
            docenteAux.setEstado(false);
            repo.editar(docenteAux);

        } else {
            throw new ObjectNotFoundException("El id del docente no existe");
        }

    }

    @Override
    public void habilitar(int id) throws ObjectNotFoundException, NoResponseBDException {
        Docente docenteAux = repo.obtenerPorId(id);
        if (docenteAux != null) {
            docenteAux.setEstado(true);
            repo.editar(docenteAux);

        } else {
            throw new ObjectNotFoundException("El id del docente no existe");
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
        return null;
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
            Docente docente = repo.obtenerPorId(id);
            if (docente != null) {
                repo.eliminar(docente);
            } else {
                throw new ObjectNotFoundException("El id del docente no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    /**
     * Elimina un docente de acuerdo al id especificado si no tiene estudiantes
     *
     * @param id
     * @throws ObjectNotFoundException
     * @throws NoResponseBDException
     */
    @Override
    public void eliminarSoloDocente(int id) throws ObjectNotFoundException, NoResponseBDException, RegisteredObjectException {
        try {
            Docente docente = repo.obtenerPorId(id);
            if (docente != null) {
                Integer nEstudiantes = repo.contarEstudiantes(docente);
                if (nEstudiantes == 0) {
                    repo.eliminar(docente);
                } else {
                    throw new RegisteredObjectException("No se puede eliminar este docente porque tiene estudiantes asociados");
                }
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
            Logger.getLogger(LogicaDocente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogicaDocente.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LogicaDocente.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ois.close();
        }
        return docentes;

    }

    @Override
    public void asociarDocenteMateria(DocenteMateria docenteMateria) throws IdRequiredException, RegisteredObjectException, ObjectNotFoundException {
        try {
            if (docenteMateria.getDocente().getId() == null || docenteMateria.getMateria().getId() == null) {
                throw new IdRequiredException("Id docente e id materia requeridos");
            }
            
            DocenteMateria docMat = repoDocenteMateria.obtener(docenteMateria.getDocente().getId(), docenteMateria.getMateria().getId());

            if (docMat != null) {
                throw new RegisteredObjectException("La relacion ya existe");
            }

            Docente docente = repo.obtenerPorId(docenteMateria.getDocente().getId());
            Materia materia = repoMateria.obtenerPorId(docenteMateria.getMateria().getId());

            if (docente == null || materia == null) {
                throw new ObjectNotFoundException("El id de docente y/o materia no existe");
            }

            repoDocenteMateria.guardar(docenteMateria);
        } catch (RegisteredObjectException ex) {
            throw new RegisteredObjectException(ex.getMessage());
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        } catch (IdRequiredException ex) {
            throw new IdRequiredException(ex.getMessage());
        }
    }

    @Override
    public List<DocenteMateriaPOJO> listarDocenteMateria(Integer idDocente) throws ObjectNotFoundException {
        try {
            Docente docente = repo.obtenerPorId(idDocente);

            if (docente == null) {
                throw new ObjectNotFoundException("El id del docente no existe");
            }

            List<DocenteMateria> listaDocenteMateria = repoDocenteMateria.listarDocenteMateria(idDocente);
            List<DocenteMateriaPOJO> lista = new ArrayList<>();
            for (DocenteMateria lis : listaDocenteMateria) {
                ModelMapper modelMapper = new ModelMapper();
                DocenteMateriaPOJO docenteMateriaPOJO = modelMapper.map(lis, DocenteMateriaPOJO.class);
                docenteMateriaPOJO.getDocente().setEstudiantes(null);
                // docenteMateriaPOJO.setDocente(null);
                lista.add(docenteMateriaPOJO);
            }
            return lista;
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void eliminarDocenteMateria(int idDocente, int idMateria) throws ObjectNotFoundException, NoResponseBDException {
        try {
            DocenteMateria docenteMateria = repoDocenteMateria.obtener(idDocente, idMateria);
            if (docenteMateria != null) {
                repoDocenteMateria.eliminar(docenteMateria);
            } else {
                throw new ObjectNotFoundException("El id del docente y/o materia no existe");
            }
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
    }

}
