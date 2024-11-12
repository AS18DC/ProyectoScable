import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Scrabble ===");
            System.out.println("1. Iniciar Juego");
            System.out.println("2. Continuar Partida");
            System.out.println("4. Ver Puntaje de Jugador");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            System.out.println();
            System.out.println();

            switch (opcion) {
                case 1:
                    Jugador jugador1 = new Jugador("", "");
                    Jugador jugador2 = new Jugador("", "");
                    Juego juego = new Juego(jugador1, jugador2);
                    juego.iniciarPartida();


                    break;
                case 2:
                    System.out.println("Has seleccionado la Opción 2.");
                    // Aquí puedes agregar la lógica para la Opción 2
                    break;
                case 3:
                    System.out.println("Has seleccionado la Opción 3.");
                    // Aquí puedes agregar la lógica para la Opción 3
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 4.");
            }

            System.out.println(); // Línea en blanco para mejor legibilidad

        } while (opcion != 4);
    }
}