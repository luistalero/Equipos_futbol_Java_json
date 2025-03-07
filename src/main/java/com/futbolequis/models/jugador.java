package com.futbolequis.models;

public class jugador {
    private String nombre;
    private int edad;
    private String posicion;
    private int numero;

    public jugador() {
    }

    public jugador(String nombre, int edad, String posicion, int numero) {
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", posicion='" + posicion + '\'' +
                ", numero=" + numero +
                '}';
    }
}



// package com.futbolequis.models;

// public class jugador {
//     private int numeroJersey;
//     private String nombre;
//     private String ciudad;
//     private int edad;
//     private String dorsal;
    
//     // Constructor por defecto para Jackson
//     public jugador() {
//     }
    
//     public jugador(int numeroJersey, String nombre, String ciudad, int edad, String dorsal) {
//         this.numeroJersey = numeroJersey;
//         this.nombre = nombre;
//         this.ciudad = ciudad;
//         this.edad = edad;
//         this.dorsal = dorsal;
//     }
    
//     // Getters y setters
//     public int getNumeroJersey() {
//         return numeroJersey;
//     }
    
//     public void setNumeroJersey(int numeroJersey) {
//         this.numeroJersey = numeroJersey;
//     }
    
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
    
//     public int getEdad() {
//         return edad;
//     }
    
//     public void setEdad(int edad) {
//         this.edad = edad;
//     }
    
//     public String getDorsal() {
//         return dorsal;
//     }
    
//     public void setDorsal(String dorsal) {
//         this.dorsal = dorsal;
//     }
// }

