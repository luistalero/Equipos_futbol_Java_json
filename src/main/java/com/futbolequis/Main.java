package com.futbolequis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EquipoService equipoService;

    public static void main(String[] args) {
        equipoService = new EquipoService();
        
        boolean running = true;
        while (running) {
            String menu = """
            ========================================
            ===== GESTION DE EQUIPOS DE FUTBOL =====
            ========================================
            1. Agregar nuevo equipo
            2. Ver todos los equipos       
            3. Actualizar equipo
            4. Eliminar equipo
            5. Agregar jugador a equipo
            6. Ver jugadores del equipo
            7. Actualizar jugador
            8. Eliminar jugador
            0. Salir
            """;
            System.out.println(menu);
            System.out.print("Seleccione una opción: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addTeam();
                    break;
                case 2:
                    viewAllTeams();
                    break;
                case 3:
                    updateTeam();
                    break;
                case 4:
                    deleteTeam();
                    break;
                case 5:
                    addPlayer();
                    break;
                case 6:
                    viewTeamPlayers();
                    break;
                case 7:
                    updatePlayer();
                    break;
                case 8:
                    deletePlayer();
                    break;
                case 0:
                    running = false;
                    System.out.println("Saliendo de la aplicación. ¡Adiós!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor intente de nuevo.");
            }
        }
    }
    
    private static void addTeam() {
        System.out.println("\n=== AGREGAR NUEVO EQUIPO ===");
        
        System.out.print("Nombre del equipo: ");
        String name = scanner.nextLine();
        
        System.out.print("Ciudad: ");
        String city = scanner.nextLine();
        
        System.out.print("Fecha de fundación (dd/MM/yyyy): ");
        Date foundationDate = getDateInput();
        
        System.out.print("Nombre del presidente: ");
        String president = scanner.nextLine();
        
        Equipo equipo = new Equipo(name, city, foundationDate, president, new ArrayList<>());
        equipoService.agregarEquipo(equipo);
        
        System.out.println("¡Equipo agregado exitosamente!");
    }
    
    private static void viewAllTeams() {
        System.out.println("\n=== TODOS LOS EQUIPOS ===");
        
        List<Equipo> equipos = equipoService.getEquipos();
        if (equipos.isEmpty()) {
            System.out.println("No se encontraron equipos.");
            return;
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);
            System.out.println((i + 1) + ". " + equipo.getNombre() + " (" + equipo.getCiudad() + ")");
            System.out.println("   Fundado: " + dateFormat.format(equipo.getFechaFundacion()));
            System.out.println("   Presidente: " + equipo.getPresidente());
            System.out.println("   Jugadores: " + equipo.getJugadores().size());
            System.out.println();
        }
    }
    
    private static void updateTeam() {
        System.out.println("\n=== ACTUALIZAR EQUIPO ===");
        
        Equipo equipo = selectTeam();
        if (equipo == null) return;
        
        System.out.println("Deje el campo vacío para mantener el valor actual");
        
        System.out.print("Nombre del equipo [" + equipo.getNombre() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) equipo.setNombre(name);
        
        System.out.print("Ciudad [" + equipo.getCiudad() + "]: ");
        String city = scanner.nextLine();
        if (!city.isEmpty()) equipo.setCiudad(city);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Fecha de fundación [" + dateFormat.format(equipo.getFechaFundacion()) + "]: ");
        String dateStr = scanner.nextLine();
        if (!dateStr.isEmpty()) {
            try {
                equipo.setFechaFundacion(dateFormat.parse(dateStr));
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Manteniendo valor actual.");
            }
        }
        
        System.out.print("Nombre del presidente [" + equipo.getPresidente() + "]: ");
        String president = scanner.nextLine();
        if (!president.isEmpty()) equipo.setPresidente(president);
        
        equipoService.actualizarEquipo(equipo);
        System.out.println("¡Equipo actualizado exitosamente!");
    }
    
    private static void deleteTeam() {
        System.out.println("\n=== ELIMINAR EQUIPO ===");
        
        Equipo equipo = selectTeam();
        if (equipo == null) return;
        
        System.out.print("¿Está seguro que desea eliminar " + equipo.getNombre() + "? (s/n): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("s")) {
            equipoService.eliminarEquipo(equipo);
            System.out.println("¡Equipo eliminado exitosamente!");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
    
    private static void addPlayer() {
        System.out.println("\n=== AGREGAR JUGADOR ===");
        
        Equipo equipo = selectTeam();
        if (equipo == null) return;
        
        System.out.print("Nombre del jugador: ");
        String name = scanner.nextLine();
        
        System.out.print("Número de dorsal: ");
        int jerseyNumber = getIntInput();
        
        System.out.print("Ciudad: ");
        String city = scanner.nextLine();
        
        System.out.print("Edad: ");
        int age = getIntInput();
        
        System.out.print("Dorsal (camiseta): ");
        String dorsal = scanner.nextLine();
        
        jugador jugador = new jugador(jerseyNumber, name, city, age, dorsal);
        equipo.getJugadores().add(jugador);
        equipoService.actualizarEquipo(equipo);
        
        System.out.println("¡Jugador agregado exitosamente!");
    }
    
    private static void viewTeamPlayers() {
        System.out.println("\n=== VER JUGADORES DEL EQUIPO ===");
        
        Equipo equipo = selectTeam();
        if (equipo == null) return;
        
        List<jugador> jugadores = equipo.getJugadores();
        if (jugadores.isEmpty()) {
            System.out.println("No se encontraron jugadores para este equipo.");
            return;
        }
        
        System.out.println("Jugadores de " + equipo.getNombre() + ":");
        for (int i = 0; i < jugadores.size(); i++) {
            jugador jugador = jugadores.get(i);
            System.out.println((i + 1) + ". #" + jugador.getNumeroJersey() + " - " + jugador.getNombre());
            System.out.println("   Ciudad: " + jugador.getCiudad());
            System.out.println("   Edad: " + jugador.getEdad());
            System.out.println("   Dorsal: " + jugador.getDorsal());
            System.out.println();
        }
    }
    
    private static void updatePlayer() {
        System.out.println("\n=== ACTUALIZAR JUGADOR ===");
        
        Equipo equipo = selectTeam();
        if (equipo == null) return;
        
        jugador jugador = selectPlayer(equipo);
        if (jugador == null) return;
        
        System.out.println("Deje el campo vacío para mantener el valor actual");
        
        System.out.print("Nombre del jugador [" + jugador.getNombre() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) jugador.setNombre(name);
        
        System.out.print("Número de dorsal [" + jugador.getNumeroJersey() + "]: ");
        String jerseyStr = scanner.nextLine();
        if (!jerseyStr.isEmpty()) {
            try {
                jugador.setNumeroJersey(Integer.parseInt(jerseyStr));
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Manteniendo valor actual.");
            }
        }
        
        System.out.print("Ciudad [" + jugador.getCiudad() + "]: ");
        String city = scanner.nextLine();
        if (!city.isEmpty()) jugador.setCiudad(city);
        
        System.out.print("Edad [" + jugador.getEdad() + "]: ");
        String ageStr = scanner.nextLine();
        if (!ageStr.isEmpty()) {
            try {
                jugador.setEdad(Integer.parseInt(ageStr));
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Manteniendo valor actual.");
            }
        }
        
        System.out.print("Dorsal [" + jugador.getDorsal() + "]: ");
        String dorsal = scanner.nextLine();
        if (!dorsal.isEmpty()) jugador.setDorsal(dorsal);
        
        equipoService.actualizarEquipo(equipo);
        System.out.println("¡Jugador actualizado exitosamente!");
    }
    
    private static void deletePlayer() {
        System.out.println("\n=== ELIMINAR JUGADOR ===");
        
        Equipo equipo = selectTeam();
        if (equipo == null) return;
        
        jugador jugador = selectPlayer(equipo);
        if (jugador == null) return;
        
        System.out.print("¿Está seguro que desea eliminar a " + jugador.getNombre() + "? (s/n): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("s")) {
            equipo.getJugadores().remove(jugador);
            equipoService.actualizarEquipo(equipo);
            System.out.println("¡Jugador eliminado exitosamente!");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
    
    private static Equipo selectTeam() {
        List<Equipo> equipos = equipoService.getEquipos();
        if (equipos.isEmpty()) {
            System.out.println("No se encontraron equipos.");
            return null;
        }
        
        System.out.println("Seleccione un equipo:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
        
        System.out.print("Ingrese el número del equipo: ");
        int teamIndex = getIntInput() - 1;
        
        if (teamIndex >= 0 && teamIndex < equipos.size()) {
            return equipos.get(teamIndex);
        } else {
            System.out.println("Selección de equipo inválida.");
            return null;
        }
    }
    
    private static jugador selectPlayer(Equipo equipo) {
        List<jugador> jugadores = equipo.getJugadores();
        if (jugadores.isEmpty()) {
            System.out.println("No se encontraron jugadores para este equipo.");
            return null;
        }
        
        System.out.println("Seleccione un jugador:");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i).getNombre());
        }
        
        System.out.print("Ingrese el número del jugador: ");
        int playerIndex = getIntInput() - 1;
        
        if (playerIndex >= 0 && playerIndex < jugadores.size()) {
            return jugadores.get(playerIndex);
        } else {
            System.out.println("Selección de jugador inválida.");
            return null;
        }
    }
    
    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un número válido: ");
            }
        }
    }
    
    private static Date getDateInput() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            try {
                String input = scanner.nextLine();
                return dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.print("Por favor ingrese una fecha válida (dd/MM/yyyy): ");
            }
        }
    }
}

