package lad.com.alura.conversormoneda.vista;
public class Conversor {
    public static void eleccionUserMenu() {
        System.out.print("""
                Sea bienvenido/a al Conversor de Moneda =]
                
                ==================================================
                1) Dólar           =>> Peso argentino
                2) Peso argentino  =>> Dólar
                3) Dólar           =>> Real brasileño
                4) Real brasileño  =>> Dólar
                5) Dólar           =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Historial de conversiones
                8) Salir
                ==================================================
                Elija una opción válida:
                """);
    }
}