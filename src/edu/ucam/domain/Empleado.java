// 
// Decompiled by Procyon v0.5.36
// 

package edu.ucam.domain;

import java.io.Serializable;

public class Empleado implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String apellidos;
    
    public Empleado() {
    }
    
    public Empleado(final String id, final String nombre, final String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(final String apellidos) {
        this.apellidos = apellidos;
    }
}
