// 
// Decompiled by Procyon v0.5.36
// 

package edu.ucam.domain;

import java.util.ArrayList;
import java.util.Hashtable;
import java.io.Serializable;
/**
 * <p> 
 * Esta es la clase en la que se crean los clubes
 * </p>
 */
public class Club implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private Hashtable<String, Jugador> jugadores;
    
    public Club() {
        this.jugadores = new Hashtable<String, Jugador>();
    }
    
    public Club(final String id, final String nombre) {
        this.jugadores = new Hashtable<String, Jugador>();
        this.id = id;
        this.nombre = nombre;
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
    /**
     * Anade un jugador al club
     */
    public void addJugador(final String id, final Jugador jugador) {
        this.jugadores.put(id, jugador);
    }
    /**
     * Actualiza un jugador que esta en un club
     */
    public void updateJugador(final String id, final Jugador jugador) {
        if (this.jugadores.get(id) != null) {
            this.jugadores.replace(id, jugador);
        }
    }
    /**
     * Borra un jugador que esta en un club
     */
    public boolean removeJugador(final String id) {
        if (this.jugadores.get(id) != null) {
            this.jugadores.remove(id);
            return true;
        }
        return false;
    }
    /**
     * Imprime el numero total de jugadores
     */
    public int totalJugadores() {
        return this.jugadores.size();
    }
    /**
     * Obtiene jugadores del ArrayList jugador
     */
    public ArrayList<Jugador> getJugadores() {
        final ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        for (final Jugador jugador : this.jugadores.values()) {
            jugadores.add(jugador);
        }
        return jugadores;
    }
    /**
     * Devuelve un string
     */
    public String toString()
    {
    	return String.format("\tClub %s: %s \n", id, nombre) ;
    }
}
