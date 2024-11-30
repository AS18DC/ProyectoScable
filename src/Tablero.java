import java.util.Arrays;

class Tablero {
    private String[][] tablero;
    private static final String ROJO_FONDO = "\u001B[48;5;124m"; // Doble palabra
    private static final String TEXTO_NEGRO = "\u001B[30m"; // Texto negro
    private static final String AZUL_FONDO = "\u001B[48;5;33m"; // Triple palabra
    private static final String GRIS_CLARO_FONDO = "\u001B[48;5;250m"; // Fondo gris claro
    private static final String VERDE_FONDO = "\u001B[48;5;22m"; // Doble letra
    private static final String AMARILLO_FONDO = "\u001B[48;5;142m"; // Triple letra
    private static final String RESET = "\u001B[0m"; // Restablecer color

    public Tablero() {
        tablero = new String[15][15]; // Tablero de 15x15
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " "); // Inicializa con un espacio en blanco como String
        }
    }


    public void colocarMultiplicadores() {
        // Doble palabra
        tablero[0][0] = ROJO_FONDO + "2XP" + RESET;
        tablero[0][7] = ROJO_FONDO + "2XP" + RESET;
        tablero[0][14] = ROJO_FONDO + "2XP" + RESET;
        tablero[7][0] = ROJO_FONDO + "2XP" + RESET;
        tablero[7][14] = ROJO_FONDO + "2XP" + RESET;
        tablero[14][0] = ROJO_FONDO + "2XP" + RESET;
        tablero[14][7] = ROJO_FONDO + "2XP" + RESET;
        tablero[14][14] = ROJO_FONDO + "2XP" + RESET;

        // Triple palabra
        tablero[0][3] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[0][11] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[3][0] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[3][14] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[11][0] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[11][14] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[14][3] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[14][11] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET;
        tablero[7][7] = AZUL_FONDO + TEXTO_NEGRO + "3XP" + RESET; // Centro

        // Doble letra
        tablero[1][5] = VERDE_FONDO + "2XL" + RESET;
        tablero[1][9] = VERDE_FONDO + "2XL" + RESET;
        tablero[5][1] = VERDE_FONDO + "2XL" + RESET;
        tablero[5][5] = VERDE_FONDO + "2XL" + RESET;
        tablero[5][9] = VERDE_FONDO + "2XL" + RESET;
        tablero[5][13] = VERDE_FONDO + "2XL" + RESET;
        tablero[9][1] = VERDE_FONDO + "2XL" + RESET;
        tablero[9][5] = VERDE_FONDO + "2XL" + RESET;
        tablero[9][9] = VERDE_FONDO + "2XL" + RESET;
        tablero[9][13] = VERDE_FONDO + "2XL" + RESET;
        tablero[13][5] = VERDE_FONDO + "2XL" + RESET;
        tablero[13][9] = VERDE_FONDO + "2XL" + RESET;

        // Triple letra
        tablero[1][1] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
        tablero[1][13] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
        tablero[13][1] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
        tablero[13][13] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
        tablero[5][5] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
        tablero[5][9] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
        tablero[9][5] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
        tablero[9][9] = AMARILLO_FONDO + TEXTO_NEGRO + "3XL" + RESET;
    }

    public int colocarPalabra(String palabra, int fila, int col, boolean horizontal) {
        int puntaje = 0; // Variable para acumular el puntaje
        if (fila < 0 || fila >= 15 || col < 0 || col >= 15) return puntaje;

        if (horizontal) {
            if (col + palabra.length() > 15) return puntaje;

            // Verificar colisiones
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila][col + i];
                // Si la casilla está ocupada por una letra diferente, terminar
                if (!casilla.trim().isEmpty() && !casilla.equals(String.valueOf(palabra.charAt(i))) &&
                        !casilla.contains("XP") && !casilla.contains("XL")) {
                    return puntaje; // Colisión con otra letra
                }
            }

            // Colocar la palabra
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila][col + i];
                char letra = palabra.charAt(i);

                // Vaciar la casilla si tiene un multiplicador
                if (casilla.contains("XP") || casilla.contains("XL")) {
                    tablero[fila][col + i] = " "; // Vaciar la casilla

                    // Asignar puntaje según el multiplicador
                    if (casilla.contains("3XL")) {
                        puntaje += 3; // Multiplicador de 3
                    } else if (casilla.contains("2XL")) {
                        puntaje += 2; // Multiplicador de 2
                    } else if (casilla.contains("3XP")) {
                        puntaje += 3; // Multiplicador de 3 por palabra
                    } else if (casilla.contains("2XP")) {
                        puntaje += 2; // Multiplicador de 2 por palabra
                    }
                } else {
                    puntaje += 1; // Puntaje normal por letra
                }
                // Colocar la letra
                tablero[fila][col + i] = String.valueOf(letra); // Colocar la letra
            }
        } else { // Vertical
            if (fila + palabra.length() > 15) return puntaje;

            // Verificar colisiones
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila + i][col];
                // Si la casilla está ocupada por una letra diferente, terminar
                if (!casilla.trim().equals("") && !casilla.equals(String.valueOf(palabra.charAt(i))) &&
                        !casilla.contains("XP") && !casilla.contains("XL")) {
                    return puntaje; // Colisión con otra letra
                }
            }

            // Colocar la palabra
            for (int i = 0; i < palabra.length(); i++) {
                String casilla = tablero[fila + i][col];
                char letra = palabra.charAt(i);
                // Vaciar la casilla si tiene un multiplicador
                if (casilla.contains("XP") || casilla.contains("XL")) {
                    // Asignar puntaje según el multiplicador
                    if (casilla.contains("3XL")) {
                        puntaje += 3; // Multiplicador de 3
                    } else if (casilla.contains("2XL")) {
                        puntaje += 2; // Multiplicador de 2
                    } else if (casilla.contains("3XP")) {
                        puntaje += 3; // Multiplicador de 3 por palabra
                    } else if (casilla.contains("2XP")) {
                        puntaje += 2; // Multiplicador de 2 por palabra
                    }
                    tablero[fila + i][col] = " "; // Vaciar la casilla
                } else {
                    puntaje += 1; // Puntaje normal por letra
                }
                tablero[fila + i][col] = String.valueOf(letra); // Colocar la letra
            }
        }
        return puntaje;
    }

    public void mostrarTableroConColores() {
        // Imprimir encabezado de columnas
        //System.out.print("    ");
        System.out.printf("%s%s  . %s", GRIS_CLARO_FONDO, TEXTO_NEGRO, RESET);
        for (int col = 0; col < tablero[0].length; col++) {
            System.out.printf("|%s%2d %s", GRIS_CLARO_FONDO + TEXTO_NEGRO, col, RESET); // Coordenadas en gris con texto negro
        }
        System.out.println("|");
        System.out.println("-----------------------------------------------------------------");

        // Imprimir filas del tablero
        for (int fila = 0; fila < tablero.length; fila++) {
            System.out.printf("%s %s%2d %s|", GRIS_CLARO_FONDO, TEXTO_NEGRO, fila, RESET); // Coordenadas en gris
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

    public void limpiarTablero() {
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " ");
        }
    }
}