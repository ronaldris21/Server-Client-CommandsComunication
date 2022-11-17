// 
// Decompiled by Procyon v0.5.36
// 

package edu.ucam.domain;

import java.io.Serializable;

public class Jugador implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String apellidos;
    private int goles;
    
    public Jugador() {
    }
    
    public Jugador(final String id, final String nombre, final String apellidos, final int goles) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.goles = goles;
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
    
    public int getGoles() {
        return this.goles;
    }
    
    public void setGoles(final int goles) {
        this.goles = goles;
    }
    
    public void addGol() {
        ++this.goles;
    }
    
    public void removeGol() {
        if (this.goles > 0) {
            --this.goles;
        }
    }
}