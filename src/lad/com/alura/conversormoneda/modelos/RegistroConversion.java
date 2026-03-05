package lad.com.alura.conversormoneda.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record RegistroConversion(String monedaBase, String monedaObjetivo, double cantidad, double resultado, LocalDateTime fechaHora) {

    // Sobrescribimos el toString para que el historial se imprima bonito
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "[" + fechaHora.format(formato) + "] " + cantidad + " " + monedaBase + " =>> " + resultado + " " + monedaObjetivo;
    }
}