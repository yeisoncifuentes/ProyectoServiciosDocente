/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.repo.impl;

import co.edu.unicundi.entity.Docente;
import co.edu.unicundi.repo.IDocenteRepo;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author cass465
 */
@Stateless
public class DocenteRepo implements IDocenteRepo {

    @PersistenceContext(unitName = "edu.unicundi_ProyectoServiciosDocente-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager entity;

    @Override
    public void registrar(Docente docente) {
        String materias = new Gson().toJson(docente.getMaterias());
        System.out.println(materias);
        System.out.println();
        entity.createNativeQuery("INSERT INTO docente.tbl_docente(cedula, nombre, apellido, correo, fecha, materias)"
                + "VALUES (?, ?, ?, ?, ?, ?)")
                .setParameter(1, docente.getCedula())
                .setParameter(2, docente.getNombre())
                .setParameter(3, docente.getApellido())
                .setParameter(4, docente.getCorreo())
                .setParameter(5, docente.getFecha())
                .setParameter(6, materias)
                .executeUpdate();
    }

    @Override
    public List<Docente> listar() {
        Query query = this.entity.createNativeQuery("SELECT id,cedula,materias,nombre,apellido,correo, fecha FROM docente.tbl_docente");

        List<Object[]> result = query.getResultList();
        List<Docente> docentes = new ArrayList();

        for (Object[] datos : result) {

            ObjectMapper mapper = new ObjectMapper();
            List<String> materias = new ArrayList();

            try {
                materias = mapper.readValue((String) datos[2], List.class);
                docentes.add(new Docente(((Long) datos[0]).intValue(), (String) datos[1], materias, (String) datos[3], (String) datos[4], (String) datos[5], (Date) datos[6]));

            } catch (IOException ex) {
                Logger.getLogger(DocenteRepo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return docentes;
    }

    @Override
    public Docente obtenerPorCedula(String cedula) {
        TypedQuery<Docente> query = entity.createQuery("SELECT d FROM Docente d WHERE d.cedula = ?1", Docente.class);
        query.setParameter(1, cedula);

        List<Docente> resultado = query.getResultList();

        Docente docente = new Docente();

        for (Docente result : resultado) {
            docente = new Docente(result.getId(), result.getCedula(), result.getMaterias(), result.getNombre(), result.getApellido(), result.getCorreo(), result.getFecha());
        }

        return docente;
    }

    @Override
    public List<Docente> validarCedula(String cedula) {
        Query query = this.entity.createNativeQuery("SELECT id,cedula,materias,nombre,apellido,correo, fecha FROM docente.tbl_docente WHERE cedula = ?1");
        query.setParameter(1, cedula);

        List<Object[]> result = query.getResultList();
        List<Docente> docentes = new ArrayList();

        for (Object[] datos : result) {

            ObjectMapper mapper = new ObjectMapper();
            List<String> materias = new ArrayList();

            try {
                materias = mapper.readValue((String) datos[2], List.class);
                docentes.add(new Docente(((Long) datos[0]).intValue(), (String) datos[1], materias, (String) datos[3], (String) datos[4], (String) datos[5], (Date) datos[6]));

            } catch (IOException ex) {
                Logger.getLogger(DocenteRepo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return docentes;
    }

    @Override
    public List<Docente> validarCorreo(String correo) {
        Query query = this.entity.createNativeQuery("SELECT id,cedula,materias,nombre,apellido,correo, fecha FROM docente.tbl_docente WHERE correo = ?1");
        query.setParameter(1, correo);

        List<Object[]> result = query.getResultList();
        List<Docente> docentes = new ArrayList();

        for (Object[] datos : result) {

            ObjectMapper mapper = new ObjectMapper();
            List<String> materias = new ArrayList();

            try {
                materias = mapper.readValue((String) datos[2], List.class);
                docentes.add(new Docente(((Long) datos[0]).intValue(), (String) datos[1], materias, (String) datos[3], (String) datos[4], (String) datos[5], (Date) datos[6]));

            } catch (IOException ex) {
                Logger.getLogger(DocenteRepo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return docentes;
    }

    @Override
    public void editar(Docente docente) {
        String materias = new Gson().toJson(docente.getMaterias());
        entity.createNativeQuery("UPDATE docente.tbl_docente SET cedula=?, nombre=?, apellido=?, correo=?, fecha=?, materias=? WHERE id=?")
                .setParameter(1, docente.getCedula())
                .setParameter(2, docente.getNombre())
                .setParameter(3, docente.getApellido())
                .setParameter(4, docente.getCorreo())
                .setParameter(5, docente.getFecha())
                .setParameter(6, materias)
                .setParameter(7, docente.getId())
                .executeUpdate();
    }

    @Override
    public List<Docente> obtenerDocentesMateria(String materia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Docente obtenerPorId(Integer id) {
        Query query = this.entity.createNativeQuery("SELECT id,cedula,materias,nombre,apellido,correo, fecha FROM docente.tbl_docente WHERE id = ?1");
        query.setParameter(1, id);

        List<Object[]> result = query.getResultList();
        Docente docente = new Docente();

        for (Object[] datos : result) {

            ObjectMapper mapper = new ObjectMapper();
            List<String> materias = new ArrayList();

            try {
                materias = mapper.readValue((String) datos[2], List.class);
                docente = new Docente(((Long) datos[0]).intValue(), (String) datos[1], materias, (String) datos[3], (String) datos[4], (String) datos[5], (Date) datos[6]);

            } catch (IOException ex) {
                Logger.getLogger(DocenteRepo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return docente;
    }

    @Override
    public void eliminar(Docente docente) {
        //this.entity.remove(docente);
        entity.createNativeQuery("DELETE FROM docente.tbl_docente WHERE id=?1")
                .setParameter(1, docente.getId())
                .executeUpdate();
    }

}
