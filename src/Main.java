import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;

// Menús y entradas del programa
public class Main {
    public static void main(String[] args) {
        // Los dos menús trabajan con los mismos tickets
        SistemaTickets sistema = new SistemaTickets();
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in))) {
            int opcion;
            do {
                System.out.println("\n--- Sistema de tickets ---");
                System.out.println("1. Menú de usuario");
                System.out.println("2. Menú de administrador");
                System.out.println("0. Salir");
                opcion = leerEntero(entrada, "Opción: ", 0, 2);
                switch (opcion) {
                    case 1:
                        menuUsuario(entrada, sistema);
                        break;
                    case 2:
                        menuAdministrador(entrada, sistema);
                        break;
                    case 0:
                        System.out.println("Hasta luego.");
                        break;
                }
            } while (opcion != 0);
        } catch (EOFException e) {
            System.out.println("\nSe cerró la entrada. Hasta luego.");
        } catch (IOException e) {
            System.out.println("No se pudo leer la entrada: " + e.getMessage());
        }
    }

    private static void menuUsuario(BufferedReader entrada, SistemaTickets sistema)
            throws IOException {
        int opcion;
        do {
            System.out.println("\n--- Menú de usuario ---");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket resuelto");
            System.out.println("0. Volver");
            opcion = leerEntero(entrada, "Opción: ", 0, 2);
            switch (opcion) {
                case 1:
                    String nombre = leerTexto(entrada, "Nombre completo: ");
                    String descripcion = leerTexto(entrada, "Descripción del problema: ");
                    System.out.println("Prioridad: 1. Alta | 2. Media | 3. Baja");
                    int prioridad = leerEntero(entrada, "Prioridad: ", 1, 3);
                    Ticket nuevo = sistema.crearTicket(descripcion, nombre, prioridad);
                    System.out.println("Ticket creado. Guardá tu ID: " + nuevo.getId());
                    break;
                case 2:
                    int id = leerEntero(entrada, "ID del ticket: ", 1, Integer.MAX_VALUE);
                    Ticket encontrado = sistema.buscarResuelto(id);
                    if (encontrado == null) {
                        System.out.println("El ticket #" + id + " está pendiente.");
                    } else {
                        System.out.println("\nTicket resuelto:");
                        System.out.println(encontrado);
                    }
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    private static void menuAdministrador(BufferedReader entrada, SistemaTickets sistema)
            throws IOException {
        int opcion;
        do {
            System.out.println("\n--- Menú de administrador ---");
            System.out.println("1. Ver ticket al frente");
            System.out.println("2. Resolver ticket al frente");
            System.out.println("0. Volver");
            opcion = leerEntero(entrada, "Opción: ", 0, 2);
            switch (opcion) {
                case 1:
                    Ticket siguiente = sistema.verSiguiente();
                    if (siguiente == null) {
                        System.out.println("No hay tickets pendientes.");
                    } else {
                        System.out.println("\nSiguiente ticket:");
                        System.out.println(siguiente);
                    }
                    break;
                case 2:
                    Ticket resuelto = sistema.resolverSiguiente();
                    if (resuelto == null) {
                        System.out.println("No hay tickets pendientes.");
                    } else {
                        System.out.println("\nTicket resuelto correctamente:");
                        System.out.println(resuelto);
                    }
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    private static int leerEntero(BufferedReader entrada, String mensaje, int minimo, int maximo)
            throws IOException {
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.readLine();
            if (texto == null) {
                throw new EOFException();
            }
            try {
                int numero = Integer.parseInt(texto.trim());
                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }
                System.out.println("Ingresá un número entre " + minimo + " y " + maximo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número entero válido.");
            }
        }
    }

    private static String leerTexto(BufferedReader entrada, String mensaje) throws IOException {
        // Para no crear tickets con datos vacíos
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.readLine();
            if (texto == null) {
                throw new EOFException();
            }
            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }
            System.out.println("Este campo no puede quedar vacío.");
        }
    }
}
