import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Juego{
    private Jugador jugador1;
    private Jugador jugador2;
    private Saco saco;
    private Tablero tablero;
    private long tiempoInicio;
    private Diccionario diccionario;


    public int validarNumero(){
        Scanner scanner = new Scanner(System.in);
        int numero=0;
        boolean entradaValida = false;
        System.out.print(" (0-14):");
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

    public Jugador seleccionJugador() {
        Scanner scanner = new Scanner(System.in);
        Gestion gestion = new Gestion();
        Jugador jugador;

        System.out.println("Coloque el nombre del jugador: ");
        String nombreExistente = scanner.nextLine();
        jugador = gestion.consultarJugador(nombreExistente);

        if (jugador != null) {
            System.out.println("Jugador encontrado.");
            return jugador; // Jugador encontrado, retornarlo
        } else {
            System.out.println("Jugador no encontrado.");
            return null; // Retorna null si el jugador no es encontrado
        }
    }

    public Juego(Jugador jugador1, Jugador jugador2, String rutaDiccionario) throws IOException {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.saco = new Saco();
        this.tablero = new Tablero(saco);
        this.tiempoInicio = System.currentTimeMillis();
        this.diccionario = new Diccionario(rutaDiccionario);
    }

    public static void pausar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
        }
    }

    private boolean validarPalabra(String palabra) {
        if (!diccionario.existePalabra(palabra)) {
            System.out.println("La palabra '" + palabra + "' no es válida.");
            return false;
        }
        return true;
    }


    ///************************************************************************************************\\\
    public void iniciarPartida() {
        System.out.println("!!! El juego ha iniciado !!!");
        System.out.println(" ");

        while (jugador1 == null) {
            System.out.println("Datos Jugador 1: ");
            jugador1 = seleccionJugador();
            if (jugador1 == null) {
                System.out.println("Error: Jugador 1 no válido. Intenta nuevamente.");
            }
        }
        System.out.println(" ");
        while (jugador2 == null || jugador2.equals(jugador1)) {
            System.out.println("Datos Jugador 2: ");
            jugador2 = seleccionJugador();
            if (jugador2 == null) {
                System.out.println("Error: Jugador 2 no válido. Intenta nuevamente.");
            } else if (jugador2.equals(jugador1)) {
                System.out.println("Error: Jugador 2 no puede ser el mismo que Jugador 1. Intenta nuevamente.");
                jugador2 = null;
            }
        }
        System.out.println("Ambos jugadores han sido seleccionados correctamente. ¡Listos para jugar!");
        System.out.println(" ");

        saco.repartirLetras(jugador1, 1);
        saco.repartirLetras(jugador2, 1);
        System.out.println();
        System.out.println("El jugador " + jugador1.getNombre() + " saco: " + jugador1.getLetras());
        System.out.println("El jugador " + jugador2.getNombre() + " saco: " + jugador2.getLetras());
        jugador1.setPuntajePartida(0);
        jugador2.setPuntajePartida(0);

        boolean turnoJugador1 = true;

        if (saco.letraMasCercanaA(jugador1.getLetras(), jugador2.getLetras())) {
            System.out.println("El jugador que tiene el primer turno es: " + jugador1.getNombre());
        } else {
            System.out.println("El jugador que tiene el primer turno es: " + jugador2.getNombre());
            turnoJugador1 = !turnoJugador1;
        }
        saco.devolverLetrasAlSaco(jugador1);
        saco.devolverLetrasAlSaco(jugador2);

        saco.repartirLetras(jugador1, 7);
        saco.repartirLetras(jugador2, 7);

        pausar(3000);
        tablero.colocarMultiplicadores();

        int contadorMovimientos = 0;
        while (true) {
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            System.out.println();
            System.out.println();
            System.out.println("**************************-SCRABBLE-*****************************");

            tablero.mostrarTableroConColores();
            System.out.println("Cantidad en el saco: "+saco.contarLetrasEnSaco());
            System.out.println();

            System.out.println("Turno de " + jugadorActual.getNombre());
            System.out.println("Puntaje es " + jugadorActual.getPuntajePartida());
            System.out.println("Letras disponibles: " + jugadorActual.getLetras());
            Scanner scanner = new Scanner(System.in);
            System.out.println("___________________________________");

            boolean turnoCompletado = false;

            while (!turnoCompletado) {
                System.out.println("Quiere cambiar alguna ficha? Si/No");
                String cambioFicha = scanner.nextLine();

                if (cambioFicha.equalsIgnoreCase("Si")) {
                    System.out.println("Quiere cambiar todas las fichas? Si/No");
                    String cambioTodasLasFichas = scanner.nextLine();

                    if (cambioTodasLasFichas.equalsIgnoreCase("Si")) {
                        saco.devolverLetrasAlSaco(jugadorActual);
                        saco.repartirLetras(jugadorActual, 7);
                        System.out.println("Nuevas Letras disponibles: " + jugadorActual.getLetras());
                    } else {
                        saco.cambiarFichas(jugadorActual);
                        System.out.println("Nueva Letra disponible: " + jugadorActual.getLetras());
                    }
                    turnoCompletado = true; // Cambio de fichas completa el turno
                } else {
                    System.out.println("Puede colocar palabra? Si/No");
                    String disponibilidadTurno = scanner.nextLine();

                    if (disponibilidadTurno.equalsIgnoreCase("Si")) {
                        System.out.print("Ingrese la palabra: ");
                        String palabra = scanner.nextLine();

                        if (!jugadorActual.verificarLetrasParaPalabra(palabra)) {
                            System.out.println("El jugador no puede jugar la palabra: " + palabra);
                            continue; // Regresa al inicio del bucle para intentar de nuevo
                        }

                        if (!validarPalabra(palabra)) {
                            System.out.println("La palabra no está en el diccionario.");
                            continue; // Regresa al inicio del bucle para intentar de nuevo
                        }

                        if (contadorMovimientos == 0) {
                            System.out.print("¿Horizontal? (true/false): ");
                            String horizontal = scanner.nextLine();

                            boolean horizontal2 = false;
                            int fila = 0;
                            int col = 0;
                            if (horizontal.equalsIgnoreCase("true")) {
                                horizontal2 = true;
                                fila = 7;
                                col = 7 - (palabra.length() / 2);
                            } else {
                                fila = 7 - (palabra.length() / 2);
                                col = 7;
                            }
                            int puntosGanados = tablero.colocarPalabra(palabra, fila, col, horizontal2, jugadorActual);

                            if (puntosGanados > 0) {
                                ArrayList<String> usadas = new ArrayList<>();
                                for (char letra : palabra.toCharArray()) {
                                    usadas.add(String.valueOf(letra));
                                }

                                // Actualizar el puntaje del jugador
                                System.out.println("Ganaste "+puntosGanados+" puntos");
                                jugadorActual.setPuntajePartida(jugadorActual.getPuntajePartida() + puntosGanados);
                                contadorMovimientos++;

                                usadas.replaceAll(String::toUpperCase);
                                jugadorActual.usarLetras(usadas);
                                saco.repartirLetras(jugadorActual, usadas.size());


                                turnoCompletado = true; // Marcar el turno como completado
                        } else {
                                System.out.println("No se pudo colocar la palabra. Intente de nuevo.");
                                continue;
                            }
                        } else {
                            System.out.print("Ingrese fila: ");
                            int fila = validarNumero();

                            System.out.print("Ingrese columna: ");
                            int col = validarNumero();

                            System.out.print("¿Horizontal? (true/false): ");
                            String horizontal = scanner.nextLine();

                            boolean horizontal2 = false;
                            if (horizontal.equalsIgnoreCase("true")) {
                                horizontal2 = true;
                            }

                            int puntosGanados = tablero.colocarPalabra(palabra, fila, col, horizontal2, jugadorActual);

                            if (puntosGanados > 0) {
                                ArrayList<String> usadas = new ArrayList<>();
                                for (char letra : palabra.toCharArray()) {
                                    usadas.add(String.valueOf(letra));
                                }

                                // Actualizar el puntaje del jugador
                                System.out.println("Ganaste "+puntosGanados+" puntos");
                                jugadorActual.setPuntajePartida(jugadorActual.getPuntajePartida() + puntosGanados);
                                contadorMovimientos++;

                                usadas.replaceAll(String::toUpperCase);
                                jugadorActual.usarLetras(usadas);
                                saco.repartirLetras(jugadorActual, usadas.size());
                                turnoCompletado = true; // Marcar el turno como completado
                            } else {
                                System.out.println("No se pudo colocar la palabra. Intente de nuevo.");
                                continue;
                            }
                        }
                    } else {
                        turnoCompletado = true;
                    }
                }

                ///--------------------------------------------------------------------------------
            }
            if (jugador1.getLetras().isEmpty() || jugador2.getLetras().isEmpty()) {
                break; // Terminar el juego
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