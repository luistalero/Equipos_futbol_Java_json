package com.futbolequis;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class Equipo {
    private String nombre;
    private String ciudad;
    private Date fechaFundacion;
    private String presidente;
    private List<jugador> jugadores;
    
    // Constructor por defecto para Jackson
    public Equipo() {
    }
    
    public Equipo(String nombre, String ciudad, Date fechaFundacion, String presidente, List<jugador> jugadores) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fechaFundacion = fechaFundacion;
        this.presidente = presidente;
        this.jugadores = jugadores;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @JsonProperty("fechaFundacion")
    public Date getFechaFundacion() {
        return fechaFundacion;
    }
    
    @JsonProperty("fechaFundacion")
    public void setFechaFundacion(Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }
    
    public String getPresidente() {
        return presidente;
    }
    
    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }
    
    public List<jugador> getJugadores() {
        return jugadores;
    }
    
    public void setJugadores(List<jugador> jugadores) {
        this.jugadores = jugadores;
    }
}

