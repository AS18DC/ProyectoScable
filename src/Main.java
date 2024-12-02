import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestion gestion = new Gestion();
        int opcion;

        do {
            System.out.println("=== Menú Scrabble ===");
            System.out.println("1. Iniciar Juego");
            System.out.println("2. Continuar Partida");
            System.out.println("3. Registros de usuarios");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            System.out.println();
            System.out.println();

            switch (opcion) {
                case 1:
                    try {
                        Jugador jugador1 = new Jugador("", "");
                        Jugador jugador2 = new Jugador("", "");
                        String rutaDiccionario = "src/listado-general.txt";
                        Juego juego = new Juego(jugador1, jugador2, rutaDiccionario);
                        juego.iniciarPartida();
                    } catch (IOException e) {
                        System.out.println("Error al cargar el diccionario: " + e.getMessage());
                    }
                case 2:
                    System.out.println("Has seleccionado la Opción 2.");
                    break;
                case 3:
                    gestion.menuRegistro(gestion);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 4.");
            }

            System.out.println();

        } while (opcion != 4);
    }
}