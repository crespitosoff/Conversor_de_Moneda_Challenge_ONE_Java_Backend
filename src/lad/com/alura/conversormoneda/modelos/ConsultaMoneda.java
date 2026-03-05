package lad.com.alura.conversormoneda.modelos;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    // Método que hace la petición. Ya no es 'static', pertenece al objeto ConsultaMoneda.
    public double obtenerTasa(String monedaBase, String monedaObjetivo, double cantidad) {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            String url = "https://v6.exchangerate-api.com/v6/9ba310889b4d07769a662fc0/pair/" + monedaBase + "/" + monedaObjetivo + "/" + cantidad;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement elemento = JsonParser.parseString(respuesta.body());
            JsonObject objectRoot = elemento.getAsJsonObject();

            return objectRoot.get("conversion_result").getAsDouble();

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
            return 0;
        }
    }
}