package com.futbolequis.models;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String ciudad;
    private String presidente;
    private List<jugador> jugadores;
    private String fechaFundacion;

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public Equipo(String nombre, String ciudad, String presidente, String fechaFundacion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.presidente = presidente;
        this.fechaFundacion = fechaFundacion;
        this.jugadores = new ArrayList<>();
    }

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

    public void agregarJugador(jugador jugador) {
        this.jugadores.add(jugador);
    }

    public String getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(String fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", presidente='" + presidente + '\'' +
                ", jugadores=" + jugadores +
                ", fechaFundacion='" + fechaFundacion + '\'' +
                '}';
    }
}




// package com.futbolequis.models;

// import com.fasterxml.jackson.annotation.JsonProperty;

// import java.util.Date;
// import java.util.List;

// public class Equipo {
//     private String nombre;
//     private String ciudad;
//     private Date fechaFundacion;
//     private String presidente;
//     private List<jugador> jugadores;
    
//     // Constructor por defecto para Jackson    
//     public Equipo(String nombre, String ciudad, Date fechaFundacion, String presidente, List<jugador> jugadores) {
//         this.nombre = nombre;
//         this.ciudad = ciudad;
//         this.fechaFundacion = fechaFundacion;
//         this.presidente = presidente;
//         this.jugadores = jugadores;
//     }
    
//     // Getters y setters
//     public String getNombre() {
//         return nombre;
//     }
    
//     public void setNombre(String nombre) {
//         this.nombre = nombre;
//     }
    
//     public String getCiudad() {
//         return ciudad;
//     }
    
//     public void setCiudad(String ciudad) {
//         this.ciudad = ciudad;
//     }
    
//     @JsonProperty("fechaFundacion")
//     public Date getFechaFundacion() {
//         return fechaFundacion;
//     }
    
//     @JsonProperty("fechaFundacion")
//     public void setFechaFundacion(Date fechaFundacion) {
//         this.fechaFundacion = fechaFundacion;
//     }
    
//     public String getPresidente() {
//         return presidente;
//     }
    
//     public void setPresidente(String presidente) {
//         this.presidente = presidente;
//     }
    
//     public List<jugador> getJugadores() {
//         return jugadores;
//     }
    
//     public void setJugadores(List<jugador> jugadores) {
//         this.jugadores = jugadores;
//     }
// }

