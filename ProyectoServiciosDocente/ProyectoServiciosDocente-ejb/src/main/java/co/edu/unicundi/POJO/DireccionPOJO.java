/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.POJO;

/**
 * POJO de la entidad direccion
 * @author YEISON
 */
public class DireccionPOJO {
    
     private Integer id;    
    
    private String direccion;
    
    private String barrio;
    
    
    public DireccionPOJO() {
    }

    public DireccionPOJO(Integer id, String direccion, String barrio) {
        this.id = id;
        this.direccion = direccion;
        this.barrio = barrio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
    
    
}
