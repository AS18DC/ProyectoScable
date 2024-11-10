import java.util.ArrayList;
import java.util.Scanner;

class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Saco saco;
    private Tablero tablero;
    private long tiempoInicio;

    public Juego(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.saco = new Saco();
        this.tablero = new Tablero();
        this.tiempoInicio = System.currentTimeMillis();
    }

    public void iniciarPartida() {
        saco.repartirLetras(jugador1);
        saco.repartirLetras(jugador2);
        boolean turnoJugador1 = true;

        while (true) {
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            System.out.println("Turno de " + jugadorActual.getNombre());
            tablero.mostrarTablero();
            //tablero.displayBoard();
            System.out.println("Letras disponibles: " + jugadorActual.getLetras());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la palabra: ");
            String palabra = scanner.nextLine();
            System.out.print("Ingrese fila (0-14): ");
            int fila = scanner.nextInt();
            System.out.print("Ingrese columna (0-14): ");
            int col = scanner.nextInt();
            System.out.print("¿Horizontal? (true/false): ");
            String horizontal = scanner.nextLine();
            scanner.nextLine(); // Limpiar el buffer

            boolean horizontal2 = false;

            if (horizontal=="true"){
                horizontal2 = true;
            }

            if (tablero.colocarPalabra(palabra, fila, col, horizontal2)) {
                ArrayList<Character> usadas = new ArrayList<>();
                for (char letra : palabra.toCharArray()) {
                    usadas.add(letra);
                }
                jugadorActual.usarLetras(usadas);
                saco.repartirLetras(jugadorActual);
            } else {
                System.out.println("No se pudo colocar la palabra. Intente de nuevo.");
                continue;
            }

            // Verificar si un jugador se quedó sin letras
            if (jugador1.getLetras().isEmpty() || jugador2.getLetras().isEmpty()) {
                break; // Terminar el juego
            }

            turnoJugador1 = !turnoJugador1; // Cambiar turno
        }

        finalizarPartida();
    }

    public void finalizarPartida() {
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = (tiempoFin - tiempoInicio) / 1000; // Tiempo en segundos
        System.out.println("El juego ha terminado.");
        System.out.println("Ganador: " + (jugador1.getPuntaje() > jugador2.getPuntaje() ? jugador1.getNombre() : jugador2.getNombre()));
        System.out.println("Puntaje Jugador 1: " + jugador1.getPuntaje());
        System.out.println("Puntaje Jugador 2: " + jugador2.getPuntaje());
        System.out.println("Tiempo de la partida: " + tiempoTotal + " segundos.");
    }
}