import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * La clase Tablero representa el tablero de juego para un juego de palabras.
 * Contiene una matriz de Strings que representa las posiciones en el tablero y métodos para manipularlo.
 */
class Tablero {
    private String[][] tablero;
    private Saco saco;
    private static final String fondoRojo = "\u001B[48;5;124m"; // Doble palabra
    private static final String textoNegro = "\u001B[30m"; // Texto negro
    private static final String fondoAzull = "\u001B[48;5;33m"; // Triple palabra
    private static final String fondoGris = "\u001B[48;5;250m"; // Fondo gris claro
    private static final String fondoVerde = "\u001B[48;5;22m"; // Doble letra
    private static final String fondoAmarillo = "\u001B[48;5;142m"; // Triple letra
    private static final String reset = "\u001B[0m"; // Restablecer color

    /**
     * Constructor que inicializa el tablero con un saco de letras.
     *
     * @param saco el saco de letras utilizado en el juego.
     */
    public Tablero(Saco saco) {
        this.saco = saco;
        tablero = new String[15][15]; // Tablero de 15x15
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " "); // Inicializa con un espacio en blanco como String
        }
    }

    /**
     * Coloca los multiplicadores de puntos en el tablero.
     */
    public void colocarMultiplicadores() {
        // Doble palabra
        tablero[0][0] = fondoRojo + "2XP" + reset;
        tablero[0][7] = fondoRojo + "2XP" + reset;
        tablero[0][14] = fondoRojo + "2XP" + reset;
        tablero[7][0] = fondoRojo + "2XP" + reset;
        tablero[7][14] = fondoRojo + "2XP" + reset;
        tablero[14][0] = fondoRojo + "2XP" + reset;
        tablero[14][7] = fondoRojo + "2XP" + reset;
        tablero[14][14] = fondoRojo + "2XP" + reset;

        // Triple palabra
        tablero[0][3] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[0][11] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[3][0] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[3][14] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[11][0] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[11][14] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[14][3] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[14][11] = fondoAzull + textoNegro + "3XP" + reset;
        tablero[7][7] = fondoAzull + textoNegro + "3XP" + reset; // Centro

        // Doble letra
        tablero[1][5] = fondoVerde + "2XL" + reset;
        tablero[1][9] = fondoVerde + "2XL" + reset;
        tablero[5][1] = fondoVerde + "2XL" + reset;
        tablero[5][5] = fondoVerde + "2XL" + reset;
        tablero[5][9] = fondoVerde + "2XL" + reset;
        tablero[5][13] = fondoVerde + "2XL" + reset;
        tablero[9][1] = fondoVerde + "2XL" + reset;
        tablero[9][5] = fondoVerde + "2XL" + reset;
        tablero[9][9] = fondoVerde + "2XL" + reset;
        tablero[9][13] = fondoVerde + "2XL" + reset;
        tablero[13][5] = fondoVerde + "2XL" + reset;
        tablero[13][9] = fondoVerde + "2XL" + reset;

        // Triple letra
        tablero[1][1] = fondoAmarillo + textoNegro + "3XL" + reset;
        tablero[1][13] = fondoAmarillo + textoNegro + "3XL" + reset;
        tablero[13][1] = fondoAmarillo + textoNegro + "3XL" + reset;
        tablero[13][13] = fondoAmarillo + textoNegro + "3XL" + reset;
        tablero[5][5] = fondoAmarillo + textoNegro + "3XL" + reset;
        tablero[5][9] = fondoAmarillo + textoNegro + "3XL" + reset;
        tablero[9][5] = fondoAmarillo + textoNegro + "3XL" + reset;
        tablero[9][9] = fondoAmarillo + textoNegro + "3XL" + reset;
    }

    /**
     * Obtiene el puntaje correspondiente a una letra específica.
     *
     * @param letra la letra cuyo puntaje se desea obtener.
     * @return el puntaje de la letra.
     */
    private int obtenerPuntajeLetra(String letra) {
        // Convertir el carácter a mayúscula para asegurar consistencia
        String letraString = String.valueOf(letra).toUpperCase();

        // Buscar en la lista de letras del saco
        for (Letra letraSaco : saco.getLetras()) {
            if (letraSaco.letra.equalsIgnoreCase(letraString)) {
                return letraSaco.puntaje;
            }
        }

        // Si no se encuentra la letra, retornar 0
        System.out.println("Letra no encontrada: " + letraString);
        return 0;
    }

    /**
     * Coloca una palabra en el tablero y calcula el puntaje obtenido.
     *
     * @param palabra    la palabra a colocar en el tablero.
     * @param fila       la fila inicial donde se colocará la palabra.
     * @param col        la columna inicial donde se colocará la palabra.
     * @param horizontal indica si la palabra se coloca horizontalmente (true) o verticalmente (false).
     * @param jugador    el jugador que coloca la palabra.
     * @return el puntaje obtenido al colocar la palabra, o 0 si la colocación no es válida.
     */
    public int colocarPalabra(String palabra, int fila, int col, boolean horizontal, Jugador jugador) {
        int puntaje = 0; // Variable para acumular el puntaje
        int multiplicadorPalabra = 1; // Multiplicador para el puntaje total de la palabra
        List<String> letrasUsadas = new ArrayList<>(); // Letras usadas por el jugador

        // Validar límites del tablero
        if (fila < 0 || fila >= 15 || col < 0 || col >= 15) return 0;

        // Convertir palabra a mayúsculas para consistencia
        palabra = palabra.toUpperCase();

        // Verificar si el jugador tiene las letras necesarias
        for (char letra : palabra.toCharArray()) {
            if (!jugador.getLetras().contains(String.valueOf(letra))) {
                System.out.println("No tienes todas las letras para formar la palabra.");
                return 0;
            }
        }

        // Lógica para colocar la palabra (horizontal o vertical)
        try {
            if (horizontal) {
                // Verificar límites horizontales
                if (col + palabra.length() > 15) return 0;

                // Verificar colisiones y preparar colocación
                for (int i = 0; i < palabra.length(); i++) {
                    String casilla = tablero[fila][col + i];
                    char letraActual = palabra.charAt(i);

                    // Validar colocación de letra
                    if (!casilla.trim().isEmpty() &&
                            !casilla.equals(String.valueOf(letraActual)) &&
                            !casilla.contains("XP") &&
                            !casilla.contains("XL")) {
                        return 0; // Colisión con otra letra
                    }
                }

                // Colocar la palabra horizontalmente
                for (int i = 0; i < palabra.length(); i++) {
                    char letraActual = palabra.charAt(i);
                    String casillaActual = tablero[fila][col + i];

                    // Calcular puntaje de la letra
                    int puntajeLetra = obtenerPuntajeLetra(String.valueOf(letraActual));

                    // Verificar multiplicadores
                    if (casillaActual.contains("XP") || casillaActual.contains("XL")) {
                        if (casillaActual.contains("3XL")) {
                            puntajeLetra *= 3;
                        } else if (casillaActual.contains("2XL")) {
                            puntajeLetra *= 2;
                        } else if (casillaActual.contains("3XP")) {
                            multiplicadorPalabra *= 3;
                        } else if (casillaActual.contains("2XP")) {
                            multiplicadorPalabra *= 2;
                        }
                    }

                    // Colocar letra en el tablero
                    tablero[fila][col + i] = String.valueOf(letraActual);

                    // Acumular puntaje
                    puntaje += puntajeLetra;

                    // Registrar letra usada
                    letrasUsadas.add(String.valueOf(letraActual));
                }
            } else {
                // Lógica vertical (similar a la horizontal)
                if (fila + palabra.length() > 15) return 0;

                // Verificar colisiones y preparar colocación
                for (int i = 0; i < palabra.length(); i++) {
                    String casilla = tablero[fila + i][col];
                    char letraActual = palabra.charAt(i);

                    // Validar colocación de letra
                    if (!casilla.trim().isEmpty() &&
                            !casilla.equals(String.valueOf(letraActual)) &&
                            !casilla.contains("XP") &&
                            !casilla.contains("XL")) {
                        return 0; // Colisión con otra letra
                    }
                }

                // Colocar la palabra verticalmente
                for (int i = 0; i < palabra.length(); i++) {
                    char letraActual = palabra.charAt(i);
                    String casillaActual = tablero[fila + i][col];

                    // Calcular puntaje de la letra
                    int puntajeLetra = obtenerPuntajeLetra(String.valueOf(letraActual));

                    // Verificar multiplicadores
                    if (casillaActual.contains("XP") || casillaActual.contains("XL")) {
                        if (casillaActual.contains("3XL")) {
                            puntajeLetra *= 3;
                        } else if (casillaActual.contains("2XL")) {
                            puntajeLetra *= 2;
                        } else if (casillaActual.contains("3XP")) {
                            multiplicadorPalabra *= 3;
                        } else if (casillaActual.contains("2XP")) {
                            multiplicadorPalabra *= 2;
                        }
                    }

                    // Colocar letra en el tablero
                    tablero[fila + i][col] = String.valueOf(letraActual);

                    // Acumular puntaje
                    puntaje += puntajeLetra;

                    // Registrar letra usada
                    letrasUsadas.add(String.valueOf(letraActual));
                }
            }

            // Aplicar multiplicador de palabra
            puntaje *= multiplicadorPalabra;

            // Quitar letras usadas del jugador
            jugador.usarLetras(letrasUsadas);

            return puntaje;

        } catch (Exception e) {
            System.out.println("Error al colocar la palabra: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Muestra el tablero de juego con los colores y los multiplicadores correspondientes.
     */
    public void mostrarTableroConColores() {
        // Imprimir encabezado de columnas
        System.out.printf("%s%s  . %s", fondoGris, textoNegro, reset);
        for (int col = 0; col < tablero[0].length; col++) {
            System.out.printf("|%s%2d %s", fondoGris + textoNegro, col, reset); // Coordenadas en gris con texto negro
        }
        System.out.println("|");
        System.out.println("-----------------------------------------------------------------");

        // Imprimir filas del tablero
        for (int fila = 0; fila < tablero.length; fila++) {
            System.out.printf("%s %s%2d %s|", fondoGris, textoNegro, fila, reset); // Coordenadas en gris
            for (int col = 0; col < tablero[fila].length; col++) {
                String letra = tablero[fila][col];

                // No aplicar fondo a las letras del tablero
                System.out.printf("%s", centrarTexto(letra, 3)); // Solo texto sin fondo
                if (col < tablero[fila].length - 1) {
                    System.out.print("|"); // Separador entre columnas
                }
            }
            System.out.println("|");
            // Imprimir línea divisoria
            System.out.println("-----------------------------------------------------------------");
        }
        System.out.println("3X= x3 en letra(L) o palabra(P)      2X= x2 en letra(L) o palabra(P)");
    }

    /**
     * Centra el texto dentro de un ancho especificado.
     *
     * @param texto el texto a centrar.
     * @param ancho el ancho en el que se centrará el texto.
     * @return el texto centrado.
     */
    public String centrarTexto(String texto, int ancho) {
        if (texto.trim().isEmpty()) {
            return " ".repeat(ancho);
        }
        if (ancho < texto.length()) {
            return texto; // Si el ancho es menor que el texto, devolver el texto sin centrar
        }
        int espaciosIzquierda = (ancho - texto.length()) / 2;
        int espaciosDerecha = ancho - texto.length() - espaciosIzquierda;
        return " ".repeat(espaciosIzquierda) + texto + " ".repeat(espaciosDerecha);
    }

    /**
     * Limpia el tablero, llenándolo con espacios en blanco.
     */
    public void limpiarTablero() {
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " ");
        }
    }
}