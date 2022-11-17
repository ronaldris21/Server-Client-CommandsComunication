// 
// Decompiled by Procyon v0.5.36
// 

package edu.ucam.domain;

public class Partido
{
    private Club equipoLocal;
    private Club equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    
    public Partido() {
    }
    
    public Partido(final Club equipoLocal, final Club equipoVisitante, final int golesLocal, final int golesVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }
    
    public Club getEquipoLocal() {
        return this.equipoLocal;
    }
    
    public void setEquipoLocal(final Club equipoLocal) {
        this.equipoLocal = equipoLocal;
    }
    
    public Club getEquipoVisitante() {
        return this.equipoVisitante;
    }
    
    public void setEquipoVisitante(final Club equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }
    
    public int getGolesLocal() {
        return this.golesLocal;
    }
    
    public void setGolesLocal(final int golesLocal) {
        this.golesLocal = golesLocal;
    }
    
    public int getGolesVisitante() {
        return this.golesVisitante;
    }
    
    public void setGolesVisitante(final int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    
    public int getPuntosLocal() {
        if (this.golesLocal > this.golesVisitante) {
            return 3;
        }
        if (this.golesLocal < this.golesVisitante) {
            return 0;
        }
        return 1;
    }
    
    public int getPuntosVisitante() {
        if (this.golesLocal < this.golesVisitante) {
            return 3;
        }
        if (this.golesLocal > this.golesVisitante) {
            return 0;
        }
        return 1;
    }
}
