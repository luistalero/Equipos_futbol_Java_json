package com.futbolequis.persistencia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futbolequis.models.Equipo;
import com.futbolequis.models.jugador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorEquipos {
    private static final String ARCHIVO_EQUIPOS = "equipos.json";
    private List<Equipo> equipos;
    private ObjectMapper objectMapper;

    public GestorEquipos() {
        this.objectMapper = new ObjectMapper();
        this.equipos = cargarEquipos();
    }

    private List<Equipo> cargarEquipos() {
        try {
            File archivo = new File(ARCHIVO_EQUIPOS);
            if (archivo.exists()) {
                return objectMapper.readValue(archivo, new TypeReference<List<Equipo>>() {});
            }
        } catch (IOException e) {
            System.err.println("Error al cargar equipos: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public void guardarEquipos() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARCHIVO_EQUIPOS), equipos);
            System.out.println("Equipos guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar equipos: " + e.getMessage());
        }
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
        guardarEquipos();
    }
    
    public void eliminarEquipo(int indice) {
        if (indice >= 0 && indice < equipos.size()) {
            equipos.remove(indice);
            guardarEquipos();
        }
    }

    public void mostrarEquipos() {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        
        System.out.println("\n=== EQUIPOS REGISTRADOS ===");
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);
            System.out.println((i + 1) + ". " + equipo.getNombre() + " (" + equipo.getCiudad() + ")");
            System.out.println("   Presidente: " + equipo.getPresidente());
            System.out.println("   Fundación: " + equipo.getFechaFundacion());
            System.out.println("   Jugadores: " + equipo.getJugadores().size());
            
            List<jugador> jugadores = equipo.getJugadores();
            if (!jugadores.isEmpty()) {
                System.out.println("   Lista de jugadores:");
                for (int j = 0; j < jugadores.size(); j++) {
                    jugador jugador = jugadores.get(j);
                    System.out.println("      " + (j + 1) + ". " + jugador.getNombre() + 
                                      " - #" + jugador.getNumero() + 
                                      " - " + jugador.getPosicion() + 
                                      " - " + jugador.getEdad() + " años");
                }
            }
            System.out.println();
        }
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }
}




// package com.futbolequis.persistencia;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.futbolequis.models.Equipo;
// import com.fasterxml.jackson.databind.DeserializationFeature;
// import java.io.File;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.List;

// public class EquipoService {
//     private static final String JSON_FILE = "equipos.json";
//     private static final ObjectMapper mapper = new ObjectMapper();
//     private List<Equipo> equipos;

//     public EquipoService() {
//         // Configurar ObjectMapper
//         mapper.enable(SerializationFeature.INDENT_OUTPUT);
//         mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
//         mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
//         this.equipos = new ArrayList<>();
//         cargarDatos();
//     }

//     public void cargarDatos() {
//         File file = new File(JSON_FILE);
//         if (file.exists()) {
//             try {
//                 equipos = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Equipo.class));
//                 System.out.println("Base de datos cargada exitosamente.");
//             } catch (IOException e) {
//                 System.out.println("Error al cargar la base de datos: " + e.getMessage());
//                 equipos = new ArrayList<>();
//             }
//         } else {
//             equipos = new ArrayList<>();
//             System.out.println("Nueva base de datos creada.");
//         }
//     }

//     public void guardarDatos() {
//         try {
//             mapper.writeValue(new File(JSON_FILE), equipos);
//             System.out.println("Base de datos guardada exitosamente.");
//         } catch (IOException e) {
//             System.out.println("Error al guardar la base de datos: " + e.getMessage());
//         }
//     }

//     public List<Equipo> getEquipos() {
//         return equipos;
//     }

//     public void agregarEquipo(Equipo equipo) {
//         equipos.add(equipo);
//         guardarDatos();
//     }

//     public void actualizarEquipo(Equipo equipo) {
//         guardarDatos();
//     }

//     public void eliminarEquipo(Equipo equipo) {
//         equipos.remove(equipo);
//         guardarDatos();
//     }
// }


