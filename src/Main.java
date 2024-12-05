
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Juego juego;
    private static boolean partidaIniciada = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestion gestion = new Gestion();
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            System.out.println();

            switch (opcion) {
                case 1:
                    iniciarJuego(scanner);
                    break;
                    
                case 2:
                    continuarPartida(scanner);
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

        scanner.close(); // Cerrar el scanner al final
    }

    private static void mostrarMenu() {
        System.out.println("=== Menú Scrabble ===");
        System.out.println("1. Iniciar Juego");
        System.out.println("2. Continuar Partida");
        System.out.println("3. Registros de usuarios");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void iniciarJuego(Scanner scanner) {
        try {
            Jugador jugador1 = new Jugador("", "");
            Jugador jugador2 = new Jugador("", "");
            String rutaDiccionario = "src/listado-general-sin-acentos.txt";
            juego = new Juego(jugador1, jugador2, rutaDiccionario);
            partidaIniciada = true;

            if (!juego.iniciarPartida()) {
                System.out.println("Regresando al menú...");
            } else {
                System.out.println("Partida iniciada con éxito.");
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el diccionario: " + e.getMessage());
        }
    }

    private static void continuarPartida(Scanner scanner) {
        System.out.print("Ingrese el alias de la partida que desea reanudar: ");
        String alias = scanner.next(); // Leer el alias de la partida

        try {
            juego = new Juego();
            juego.cargarPartida(alias);
            partidaIniciada = true;
            juego.reanudarPartida();
            System.out.println("Partida reanudada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            System.out.println("No hay ninguna partida guardada con el alias '" + alias + "'.");
        }
    }
}
