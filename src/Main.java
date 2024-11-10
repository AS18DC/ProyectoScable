import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del Jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Ingrese el nombre del Jugador 2: ");
        String nombre2 = scanner.nextLine();

        Jugador jugador1 = new Jugador(nombre1);
        Jugador jugador2 = new Jugador(nombre2);
        Juego juego = new Juego(jugador1, jugador2);
        juego.iniciarPartida();
    }
}