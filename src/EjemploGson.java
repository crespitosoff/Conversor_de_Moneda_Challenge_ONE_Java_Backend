import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EjemploGson {
    public static void main(String[] args) {
        // Crea un objeto Java (La clase Persona es solo un ejemplo genérico)
        Persona persona = new Persona("Juan", 30);

        // Crea una instancia de Gson
        Gson gson = new Gson();

        // Serializa el objeto Java en JSON
        String json = gson.toJson(persona);

        // Imprime el JSON
        System.out.println(json);

        // Deserializa el JSON en un objeto Java
        Persona personaDeserializada = gson.fromJson(json, Persona.class);

        // Imprime el objeto Java deserializado
        System.out.println(personaDeserializada.getNombre());
    }
}