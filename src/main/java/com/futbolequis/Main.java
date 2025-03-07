package com.futbolequis;

import java.util.List;
import java.util.Scanner;

import com.futbolequis.models.jugador;
import com.futbolequis.models.Equipo;
import com.futbolequis.persistencia.GestorEquipos;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorEquipos gestorEquipos = new GestorEquipos();

    public static void main(String[] args) {
        ejecutarMenu();
    }

    private static void ejecutarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ FUTBOLEQUIS ===");
            System.out.println("1. Agregar equipo");
            System.out.println("2. Mostrar equipos");
            System.out.println("3. Eliminar equipo");
            System.out.println("4. Modificar jugadores");
            System.out.println("5. Ver jugadores por equipo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        agregarEquipo();
                        break;
                    case 2:
                        mostrarEquipos();
                        break;
                    case 3:
                        eliminarEquipo();
                        break;
                    case 4:
                        modificarJugadores();
                        break;
                    case 5:
                        verJugadoresPorEquipo();
                        break;
                    case 0:
                        System.out.println("¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = -1;
            }
        } while (opcion != 0);
    }

    private static void agregarEquipo() {
        System.out.println("\n=== AGREGAR NUEVO EQUIPO ===");
        
        System.out.print("Nombre del equipo: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        
        System.out.print("Presidente: ");
        String presidente = scanner.nextLine();
        
        System.out.print("Fecha de fundación (DD/MM/AAAA): ");
        String fechaFundacion = scanner.nextLine();
        
        Equipo nuevoEquipo = new Equipo(nombre, ciudad, presidente, fechaFundacion);
        
        agregarJugadoresAEquipo(nuevoEquipo);
        
        gestorEquipos.agregarEquipo(nuevoEquipo);
        System.out.println("\nEquipo '" + nombre + "' agregado con " + nuevoEquipo.getJugadores().size() + " jugadores.");
    }
    
    private static void agregarJugadoresAEquipo(Equipo equipo) {
        boolean seguirAgregando = true;
        
        while (seguirAgregando) {
            // Agregar jugadores al equipo
            System.out.print("¿Cuántos jugadores desea agregar al equipo? ");
            int cantidadJugadores = Integer.parseInt(scanner.nextLine());
            
            for (int i = 0; i < cantidadJugadores; i++) {
                System.out.println("\nJugador #" + (equipo.getJugadores().size() + 1));
                
                System.out.print("Nombre del jugador: ");
                String nombreJugador = scanner.nextLine();
                
                System.out.print("Edad: ");
                int edad = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Posición: ");
                String posicion = scanner.nextLine();
                
                System.out.print("Número: ");
                int numero = Integer.parseInt(scanner.nextLine());
                
                jugador jugador = new jugador(nombreJugador, edad, posicion, numero);
                equipo.agregarJugador(jugador);
            }
            
            System.out.print("\n¿Desea agregar más jugadores? (S/N): ");
            String respuesta = scanner.nextLine();
            seguirAgregando = respuesta.equalsIgnoreCase("S");
        }
    }

    private static void mostrarEquipos() {
        gestorEquipos.mostrarEquipos();
    }
    
    private static void eliminarEquipo() {
        List<Equipo> equipos = gestorEquipos.getEquipos();
        
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados para eliminar.");
            return;
        }
        
        System.out.println("\n=== ELIMINAR EQUIPO ===");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
        
        System.out.print("\nSeleccione el número del equipo a eliminar (0 para cancelar): ");
        int seleccion = Integer.parseInt(scanner.nextLine());
        
        if (seleccion == 0) {
            System.out.println("Operación cancelada.");
            return;
        }
        
        if (seleccion < 1 || seleccion > equipos.size()) {
            System.out.println("Selección inválida.");
            return;
        }
        
        Equipo equipoEliminado = equipos.get(seleccion - 1);
        gestorEquipos.eliminarEquipo(seleccion - 1);
        System.out.println("Equipo '" + equipoEliminado.getNombre() + "' eliminado correctamente.");
    }
    
    private static void modificarJugadores() {
        List<Equipo> equipos = gestorEquipos.getEquipos();
        
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        
        System.out.println("\n=== MODIFICAR JUGADORES ===");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
        
        System.out.print("\nSeleccione el número del equipo (0 para cancelar): ");
        int seleccionEquipo = Integer.parseInt(scanner.nextLine());
        
        if (seleccionEquipo == 0) {
            System.out.println("Operación cancelada.");
            return;
        }
        
        if (seleccionEquipo < 1 || seleccionEquipo > equipos.size()) {
            System.out.println("Selección inválida.");
            return;
        }
        
        Equipo equipoSeleccionado = equipos.get(seleccionEquipo - 1);
        List<jugador> jugadores = equipoSeleccionado.getJugadores();
        
        if (jugadores.isEmpty()) {
            System.out.println("El equipo no tiene jugadores registrados.");
            return;
        }
        
        System.out.println("\nJugadores del equipo " + equipoSeleccionado.getNombre() + ":");
        for (int i = 0; i < jugadores.size(); i++) {
            jugador jugador = jugadores.get(i);
            System.out.println((i + 1) + ". " + jugador.getNombre() + " - #" + jugador.getNumero() + " - " + jugador.getPosicion());
        }
        
        System.out.print("\nSeleccione el número del jugador a modificar (0 para cancelar): ");
        int seleccionJugador = Integer.parseInt(scanner.nextLine());
        
        if (seleccionJugador == 0) {
            System.out.println("Operación cancelada.");
            return;
        }
        
        if (seleccionJugador < 1 || seleccionJugador > jugadores.size()) {
            System.out.println("Selección inválida.");
            return;
        }
        
        jugador jugadorSeleccionado = jugadores.get(seleccionJugador - 1);
        
        System.out.println("\n=== MODIFICAR JUGADOR ===");
        System.out.println("Datos actuales:");
        System.out.println("Nombre: " + jugadorSeleccionado.getNombre());
        System.out.println("Edad: " + jugadorSeleccionado.getEdad());
        System.out.println("Posición: " + jugadorSeleccionado.getPosicion());
        System.out.println("Número: " + jugadorSeleccionado.getNumero());
        
        System.out.println("\nIngrese los nuevos datos (deje en blanco para mantener el valor actual):");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            jugadorSeleccionado.setNombre(nombre);
        }
        
        System.out.print("Edad: ");
        String edadStr = scanner.nextLine();
        if (!edadStr.isEmpty()) {
            jugadorSeleccionado.setEdad(Integer.parseInt(edadStr));
        }
        
        System.out.print("Posición: ");
        String posicion = scanner.nextLine();
        if (!posicion.isEmpty()) {
            jugadorSeleccionado.setPosicion(posicion);
        }
        
        System.out.print("Número: ");
        String numeroStr = scanner.nextLine();
        if (!numeroStr.isEmpty()) {
            jugadorSeleccionado.setNumero(Integer.parseInt(numeroStr));
        }
        
        gestorEquipos.guardarEquipos();
        System.out.println("Jugador modificado correctamente.");
    }
    
    private static void verJugadoresPorEquipo() {
        List<Equipo> equipos = gestorEquipos.getEquipos();
        
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        
        System.out.println("\n=== VER JUGADORES POR EQUIPO ===");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
        
        System.out.print("\nSeleccione el número del equipo (0 para cancelar): ");
        int seleccion = Integer.parseInt(scanner.nextLine());
        
        if (seleccion == 0) {
            System.out.println("Operación cancelada.");
            return;
        }
        
        if (seleccion < 1 || seleccion > equipos.size()) {
            System.out.println("Selección inválida.");
            return;
        }
        
        Equipo equipoSeleccionado = equipos.get(seleccion - 1);
        List<jugador> jugadores = equipoSeleccionado.getJugadores();
        
        System.out.println("\n=== JUGADORES DEL EQUIPO " + equipoSeleccionado.getNombre() + " ===");
        
        if (jugadores.isEmpty()) {
            System.out.println("El equipo no tiene jugadores registrados.");
            return;
        }
        
        for (int i = 0; i < jugadores.size(); i++) {
            jugador jugador = jugadores.get(i);
            System.out.println((i + 1) + ". " + jugador.getNombre());
            System.out.println("   Edad: " + jugador.getEdad() + " años");
            System.out.println("   Posición: " + jugador.getPosicion());
            System.out.println("   Número: " + jugador.getNumero());
            System.out.println();
        }
    }
}






// package com.futbolequis;

// import java.util.Scanner;

// import com.futbolequis.models.Equipo;
// import com.futbolequis.persistencia.GestorEquipos;

// public class Main {
//     private static final Scanner scanner = new Scanner(System.in);
//     private static final EquipoService gestorEquipos = new EquipoService();

//     public static void main(String[] args) {
//         boolean salir = false;
//         while (!salir) {
//             mostrarMenu();
//             int opcion = leerEntero("Seleccione una opción: ");
            
//             switch (opcion) {
//                 case 1:
//                     agregarEquipo();
//                     break;
//                 case 2:
//                     mostrarEquipos();
//                     break;
//                 case 3:
//                     salir = true;
//                     break;
//                 default:
//                     System.out.println("Opción no válida. Intente de nuevo.");
//             }
//         }
//         System.out.println("¡Hasta pronto!");
//     }

//     private static void mostrarMenu() {
//         String menu = """
//             ========================================
//             ===== GESTION DE EQUIPOS DE FUTBOL =====
//             ========================================
//             1. Agregar nuevo equipo
//             2. Ver todos los equipos       
//             3. Actualizar equipo
//             4. Eliminar equipo
//             5. Agregar jugador a equipo
//             6. Ver jugadores del equipo
//             7. Actualizar jugador
//             8. Eliminar jugador
//             0. Salir
//             """;
//             System.out.println(menu);
//     }

//     private static void agregarEquipo() {
//         System.out.println("\n--- AGREGAR EQUIPO ---");
//         String nombre = leerTexto("Nombre del equipo: ");
//         String ciudad = leerTexto("Ciudad: ");
//         String presidente = leerTexto("Presidente: ");
//         String fechaFundacion = leerTexto("Fecha de fundación (DD/MM/AAAA): ");
        
//         Equipo equipo = new Equipo(nombre, ciudad, presidente, fechaFundacion);
        
//         // Preguntar cuántos jugadores quiere agregar
//         int cantidadJugadores = leerEntero("¿Cuántos jugadores desea agregar al equipo? ");
        
//         for (int i = 0; i < cantidadJugadores; i++) {
//             System.out.println("\nJugador #" + (i + 1));
//             agregarJugador(equipo);
//         }
        
//         gestorEquipos.agregarEquipo(equipo);
//         System.out.println("Equipo agregado correctamente con " + cantidadJugadores + " jugadores.");
//     }
    
//     private static void agregarJugador(Equipo equipo) {
//         String nombre = leerTexto("Nombre del jugador: ");
//         int edad = leerEntero("Edad: ");
//         String posicion = leerTexto("Posición: ");
//         int numero = leerEntero("Número de camiseta: ");
        
//         Jugador jugador = new Jugador(nombre, edad, posicion, numero);
//         equipo.agregarJugador(jugador);
//     }

//     private static void mostrarEquipos() {
//         System.out.println("\n--- EQUIPOS REGISTRADOS ---");
//         var equipos = gestorEquipos.cargarEquipos();
        
//         if (equipos.isEmpty()) {
//             System.out.println("No hay equipos registrados.");
//             return;
//         }
        
//         for (int i = 0; i < equipos.size(); i++) {
//             Equipo equipo = equipos.get(i);
//             System.out.println((i + 1) + ". " + equipo.getNombre() + " (" + equipo.getCiudad() + ")");
//             System.out.println("   Presidente: " + equipo.getPresidente());
//             System.out.println("   Fecha de fundación: " + equipo.getFechaFundacion());
//             System.out.println("   Jugadores (" + equipo.getJugadores().size() + "):");
            
//             if (equipo.getJugadores().isEmpty()) {
//                 System.out.println("      No hay jugadores registrados.");
//             } else {
//                 for (Jugador jugador : equipo.getJugadores()) {
//                     System.out.println("      - " + jugador.getNombre() + " (#" + jugador.getNumero() + ") - " + jugador.getPosicion());
//                 }
//             }
//             System.out.println();
//         }
//     }

//     private static String leerTexto(String mensaje) {
//         System.out.print(mensaje);
//         return scanner.nextLine();
//     }

//     private static int leerEntero(String mensaje) {
//         while (true) {
//             try {
//                 System.out.print(mensaje);
//                 int valor = Integer.parseInt(scanner.nextLine());
//                 if (valor < 0) {
//                     System.out.println("Por favor, ingrese un número positivo.");
//                     continue;
//                 }
//                 return valor;
//             } catch (NumberFormatException e) {
//                 System.out.println("Por favor, ingrese un número válido.");
//             }
//         }
//     }
// }

