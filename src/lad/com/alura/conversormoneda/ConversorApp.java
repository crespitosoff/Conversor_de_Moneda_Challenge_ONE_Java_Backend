package lad.com.alura.conversormoneda;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;


        while (opcion != 7) {
            Conversor.eleccionUserMenu();
            opcion = sc.nextInt();

            System.out.println("Ingrese el valor que desea convertir: ");
            double cantidad = sc.nextDouble();

            switch (opcion) {
                case 1:
                    double P_ARG = obtenerTasa("USD", "ARS", String.valueOf(cantidad));
                    System.out.println(P_ARG);
                    break;
                case 2:
                    double DOLAR = obtenerTasa("ARS", "USD", String.valueOf(cantidad));
                    System.out.println(DOLAR);
                    break;
                case 3:
                    double R_BRA = obtenerTasa("USD", "BRL", String.valueOf(cantidad));
                    System.out.println(R_BRA);
                    break;
                case 4:
                    double USD = obtenerTasa("BRL", "USD", String.valueOf(cantidad));
                    System.out.println(USD);
                    break;
                case 5:
                    double P_COL = obtenerTasa("USD", "COP", String.valueOf(cantidad));
                    System.out.println(P_COL);
                    break;
                case 6:
                    double COL = obtenerTasa("COP", "USD", String.valueOf(cantidad));
                    System.out.println(COL);
                    break;
                case 7:
                    System.out.println("Gracias por usar el conversor.");
                    break;
            }
            System.out.println("\n");
        }
        sc.close();
    }

    public static double obtenerTasa(String base_rate, String target_rate, String amount) throws IOException, InterruptedException {
        // Creando cliente HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        // Creando URL
        String url = "https://v6.exchangerate-api.com/v6/9ba310889b4d07769a662fc0/pair/" + base_rate + "/" + target_rate + "/" + amount;

        // Creando la solicitud
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            // Enviando solicitud y recibiendo respuesta
            HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            // Conversión a JSON
            JsonElement elemento = JsonParser.parseString(respuesta.body());
            JsonObject objectRoot = elemento.getAsJsonObject();

            //Accediendo a JsonObject
            return objectRoot.get("conversion_result").getAsDouble();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (InterruptedException e) {
            throw new InterruptedException(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error desconocido: " + e.getMessage());
        }
        return 0;
    }
}
