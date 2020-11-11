/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.POJO;

import java.util.ArrayList;

/**
 *
 * @author YEISON
 */
public class GenericoPOJO<T> {

    private ArrayList<T> lista = new ArrayList< T>();
    private int paginado;

    public void add(T objeto) {
        lista.add(objeto);        
    }

    public ArrayList<T> getLista() {
        return lista;
    }

    public void setLista(ArrayList<T> lista) {
        this.lista = lista;
    }

    public int getPaginado() {
        return paginado;
    }

    public void setPaginado(int paginado) {
        this.paginado = paginado;
    }

}
