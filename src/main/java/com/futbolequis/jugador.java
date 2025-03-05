package com.futbolequis;

public class jugador {
    private int numeroJersey;
    private String nombre;
    private String ciudad;
    private int edad;
    private String dorsal;
    
    // Constructor por defecto para Jackson
    public jugador() {
    }
    
    public jugador(int numeroJersey, String nombre, String ciudad, int edad, String dorsal) {
        this.numeroJersey = numeroJersey;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
        this.dorsal = dorsal;
    }
    
    // Getters y setters
    public int getNumeroJersey() {
        return numeroJersey;
    }
    
    public void setNumeroJersey(int numeroJersey) {
        this.numeroJersey = numeroJersey;
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
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getDorsal() {
        return dorsal;
    }
    
    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }
}

