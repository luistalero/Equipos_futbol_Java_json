package com.futbolequis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EquipoService {
    private static final String JSON_FILE = "equipos.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    private List<Equipo> equipos;

    public EquipoService() {
        // Configurar ObjectMapper
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        this.equipos = new ArrayList<>();
        cargarDatos();
    }

    public void cargarDatos() {
        File file = new File(JSON_FILE);
        if (file.exists()) {
            try {
                equipos = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Equipo.class));
                System.out.println("Base de datos cargada exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al cargar la base de datos: " + e.getMessage());
                equipos = new ArrayList<>();
            }
        } else {
            equipos = new ArrayList<>();
            System.out.println("Nueva base de datos creada.");
        }
    }

    public void guardarDatos() {
        try {
            mapper.writeValue(new File(JSON_FILE), equipos);
            System.out.println("Base de datos guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la base de datos: " + e.getMessage());
        }
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
        guardarDatos();
    }

    public void actualizarEquipo(Equipo equipo) {
        guardarDatos();
    }

    public void eliminarEquipo(Equipo equipo) {
        equipos.remove(equipo);
        guardarDatos();
    }
}


