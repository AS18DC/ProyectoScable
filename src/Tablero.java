import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Tablero{
    private String[][] tablero;
    private Saco saco;
    private static final String fondoRojo = "\u001B[48;5;124m"; // Doble palabra
    private static final String textoNegro = "\u001B[30m"; // Texto negro
    private static final String fondoAzull = "\u001B[48;5;33m"; // Triple palabra
    private static final String fondoGris = "\u001B[48;5;250m"; // Fondo gris claro
    private static final String fondoVerde = "\u001B[48;5;22m"; // Doble letra
    private static final String fondoAmarillo = "\u001B[48;5;142m"; // Triple letra
    private static final String reset = "\u001B[0m"; // Restablecer color

    public Tablero(Saco saco) {
        this.saco = saco;
        tablero = new String[15][15]; // Tablero de 15x15
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " "); // Inicializa con un espacio en blanco como String
        }
    }




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

    public int colocarPalabra(String palabra, int fila, int col, boolean horizontal, Jugador jugador) {
        int puntaje = 0; // Variable para acumular el puntaje
        int multiplicadorPalabra = 1; // Multiplicador para el puntaje total de la palabra
        List<String> letrasUsadas = new ArrayList<>(); // Letras usadas por el jugador

        if (fila < 0 || fila >= 15 || col < 0 || col >= 15) return puntaje;

        if (horizontal) {
            if (col + palabra.length() > 15) return puntaje;

            // Verificar colisiones
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila][col + i];
                if (!casilla.trim().isEmpty() && !casilla.equals(String.valueOf(palabra.charAt(i))) &&
                        !casilla.contains("XP") && !casilla.contains("XL")) {
                    return puntaje; // Colisión con otra letra
                }
            }

            // Colocar la palabra
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila][col + i];
                char letra = palabra.charAt(i);
                letrasUsadas.add(String.valueOf(letra)); // Registrar la letra usada
                int puntajeLetra = obtenerPuntajeLetra(String.valueOf(letra));

                // Verificar multiplicadores
                if (casilla.contains("XP") || casilla.contains("XL")) {
                    if (casilla.contains("3XL")) {
                        puntajeLetra *= 3;
                    } else if (casilla.contains("2XL")) {
                        puntajeLetra *= 2;
                    } else if (casilla.contains("3XP")) {
                        multiplicadorPalabra *= 3;
                    } else if (casilla.contains("2XP")) {
                        multiplicadorPalabra *= 2;
                    }
                    tablero[fila][col + i] = " "; // Vaciar la casilla
                }

                tablero[fila][col + i] = String.valueOf(letra);
                puntaje += puntajeLetra;
            }
        } else { // Vertical
            if (fila + palabra.length() > 15) return puntaje;

            // Verificar colisiones
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila + i][col];
                if (!casilla.trim().isEmpty() && !casilla.equals(String.valueOf(palabra.charAt(i))) &&
                        !casilla.contains("XP") && !casilla.contains("XL")) {
                    return puntaje; // Colisión con otra letra
                }
            }

            // Colocar la palabra
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila + i][col];
                char letra = palabra.charAt(i);
                letrasUsadas.add(String.valueOf(letra)); // Registrar la letra usada
                int puntajeLetra = obtenerPuntajeLetra(String.valueOf(letra));

                // Verificar multiplicadores
                if (casilla.contains("XP") || casilla.contains("XL")) {
                    if (casilla.contains("3XL")) {
                        puntajeLetra *= 3;
                    } else if (casilla.contains("2XL")) {
                        puntajeLetra *= 2;
                    } else if (casilla.contains("3XP")) {
                        multiplicadorPalabra *= 3;
                    } else if (casilla.contains("2XP")) {
                        multiplicadorPalabra *= 2;
                    }
                    tablero[fila + i][col] = " "; // Vaciar la casilla
                }

                tablero[fila + i][col] = String.valueOf(letra);
                puntaje += puntajeLetra;
            }
        }

        // Calcular el puntaje total
        puntaje *= multiplicadorPalabra;
        jugador.usarLetras(letrasUsadas); // Remover las letras usadas del jugador

        // Repartir nuevas letras al jugador para mantener 7 si es posible
        int letrasFaltantes = 7 - jugador.getLetras().size();
        if (letrasFaltantes > 0) {
            saco.repartirLetras(jugador, letrasFaltantes);
        }

        return puntaje;
    }

    public void mostrarTableroConColores() {
        // Imprimir encabezado de columnas
        //System.out.print("    ");
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

}