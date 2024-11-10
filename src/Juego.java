import java.util.ArrayList;
import java.util.Scanner;

class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Saco saco;
    private Tablero tablero;
    private long tiempoInicio;



    public int validarNumero(){
        Scanner scanner = new Scanner(System.in);
        int numero=0;
        boolean entradaValida = false;
        System.out.println(" (0-14):");
        while (!entradaValida) {
            numero = scanner.nextInt();
                if (numero >= 0 && numero <= 14) {
                    entradaValida = true;
                }else{
                    System.out.println("Error: El número debe estar entre 0 y 14. Intente de nuevo.");
                    scanner.nextLine();
                }
        }
        return numero;
    }

    public Juego(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.saco = new Saco();
        this.tablero = new Tablero();
        this.tiempoInicio = System.currentTimeMillis();
    }



///************************************************************************************************\\\
    public void iniciarPartida() {
        saco.repartirLetras(jugador1);
        saco.repartirLetras(jugador2);
        boolean turnoJugador1 = true;

        while (true) {
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            tablero.mostrarTablero();
            //tablero.displayBoard();
            System.out.println("Turno de " + jugadorActual.getNombre());
            System.out.println("Letras disponibles: " + jugadorActual.getLetras());

    ///--------------------------------------------------------------------------------

            System.out.println("Puede colocar palabra? Si/No");
            Scanner scanner = new Scanner(System.in);
            String disponibilidadturno = scanner.nextLine();

    ///--------------------------------------------------------------------------------

            if (disponibilidadturno.equalsIgnoreCase("Si")) {



                System.out.print("Ingrese la palabra: ");
                String palabra = scanner.nextLine();
                //funcion para confirmar palabra existe y que esta en su baraja




    ///--------------------------------------------------------------------------------

                System.out.print("Ingrese fila");
                //funcion para comprobar que este en ese rango
                int fila = validarNumero();

    ///--------------------------------------------------------------------------------

                System.out.print("Ingrese columna");
                //funcion para comprobar que este en ese rango
                int col = validarNumero();

    ///--------------------------------------------------------------------------------

                System.out.print("¿Horizontal? (true/false): ");
                String horizontal = scanner.nextLine();

                boolean horizontal2 = false;

                if (horizontal.equalsIgnoreCase("true")) {
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

    ///--------------------------------------------------------------------------------

                // Verificar si un jugador se quedó sin letras
                if (jugador1.getLetras().isEmpty() || jugador2.getLetras().isEmpty()) {
                    break; // Terminar el juego
                }
            }

            turnoJugador1 = !turnoJugador1; // Cambiar turno
        }

        finalizarPartida();
    }

    public void finalizarPartida() {
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = (tiempoFin - tiempoInicio) / 1000;
        System.out.println("El juego ha terminado.");
        System.out.println("Ganador: " + (jugador1.getPuntaje() > jugador2.getPuntaje() ? jugador1.getNombre() : jugador2.getNombre()));
        System.out.println("Puntaje Jugador 1: " + jugador1.getPuntaje());
        System.out.println("Puntaje Jugador 2: " + jugador2.getPuntaje());
        System.out.println("Tiempo de la partida: " + tiempoTotal + " segundos.");
    }
}