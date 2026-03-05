package lad.com.alura.conversormoneda.controladores;

import lad.com.alura.conversormoneda.modelos.ConsultaMoneda;
import lad.com.alura.conversormoneda.vista.Conversor;
import lad.com.alura.conversormoneda.modelos.RegistroConversion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Instanciamos nuestro servicio (POO)
        ConsultaMoneda consulta = new ConsultaMoneda();
        // Creamos la lista para el Historial
        List<RegistroConversion> historial = new ArrayList<>();

        int opcion;

        while (true) { // Cambiamos la salida a la opción 8
            // Tu menú original, pero imagina que le agregamos "7) Historial" y "8) Salir"
            Conversor.eleccionUserMenu();
            opcion = sc.nextInt();

            if (opcion == 8) {
                System.out.println("Gracias por usar el conversor.");
                break;
            }

            if (opcion == 7) {
                System.out.println("\n--- HISTORIAL DE CONVERSIONES ---");
                if (historial.isEmpty()) {
                    System.out.println("No hay conversiones recientes.");
                } else {
                    historial.forEach(System.out::println); // Usando Lambdas!
                }
                System.out.println("---------------------------------\n");
                continue; // Vuelve al inicio del while
            }

            if (opcion >= 1 && opcion <= 6) {
                System.out.println("Ingrese el valor que desea convertir: ");
                double cantidad = sc.nextDouble();
                double resultado;
                String base = "", objetivo = "";

                // Asignamos las monedas según la opción
                objetivo = switch (opcion) {
                    case 1 -> {
                        base = "USD";
                        yield "ARS";
                    }
                    case 2 -> {
                        base = "ARS";
                        yield "USD";
                    }
                    case 3 -> {
                        base = "USD";
                        yield "BRL";
                    }
                    case 4 -> {
                        base = "BRL";
                        yield "USD";
                    }
                    case 5 -> {
                        base = "USD";
                        yield "COP";
                    }
                    case 6 -> {
                        base = "COP";
                        yield "USD";
                    }
                    default -> objetivo;
                };

                // Usamos el objeto para obtener la tasa
                resultado = consulta.obtenerTasa(base, objetivo, cantidad);
                System.out.println("El valor es: " + resultado + " [" + objetivo + "]\n");

                // Guardamos en el historial con la marca de tiempo actual
                RegistroConversion registro = new RegistroConversion(base, objetivo, cantidad, resultado, LocalDateTime.now());
                historial.add(registro);

            } else {
                System.out.println("Opción no válida. Intente de nuevo.\n");
            }
        }
        sc.close();
    }
}