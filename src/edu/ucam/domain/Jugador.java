// 
// Decompiled by Procyon v0.5.36
// s

package edu.ucam.domain;

import java.io.Serializable;
/**
 * <p> 
 * Esta es la clase en la que se crea el jugador
 * </p>
 */
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
    
    /**
     * Metodo que anade un gol al jugador
     */
    public void addGol() {
        ++this.goles;
    }
    
    /**
     * Metodo que quita un gol al jugador
     */
    public void removeGol() {
        if (this.goles > 0) {
            --this.goles;
        }
    }
    
    public String toString()
    {
    	return String.format("\tJugador %d: %s %s - goles: %d\n", id, nombre,apellidos,goles) ;
    }
}
