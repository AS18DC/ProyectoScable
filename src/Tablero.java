/* import java.util.Arrays;

class Tablero {
    private char[][] tablero;

    public Tablero() {
        tablero = new char[15][15];
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], ' ');
        }
    }

    public boolean colocarPalabra(String palabra, int fila, int col, boolean horizontal) {
        if (horizontal) {
            if (col + palabra.length() > 15) return false;
            for (int i = 0; i < palabra.length(); i++) {
                if (tablero[fila][col + i] != ' ' && tablero[fila][col + i] != palabra.charAt(i)) {
                    return false; // Colisión con otra letra
                }
            }
            for (int i = 0; i < palabra.length(); i++) {
                tablero[fila][col + i] = palabra.charAt(i); // Colocar la palabra
            }
        } else { // Vertical
            if (fila + palabra.length() > 15) return false;
            for (int i = 0; i < palabra.length(); i++) {
                if (tablero[fila + i][col] != ' ' && tablero[fila + i][col] != palabra.charAt(i)) {
                    return false; // Colisión con otra letra
                }
            }
            for (int i = 0; i < palabra.length(); i++) {
                tablero[fila + i][col] = palabra.charAt(i); // Colocar la palabra
            }
        }
        return true;
    }

    public void colocarMultiplicadores() {
        tablero[0][0] = '3';
    }



    public void mostrarTablero() {
        System.out.print("   ");
        for (int col = 0; col < tablero[0].length; col++) {
            System.out.printf(" %2d ", col);
        }
        System.out.println();


        for (int fila = 0; fila < tablero.length; fila++) {
            System.out.printf("%2d  ", fila);
            for (char letra : tablero[fila]) {
                System.out.print("[" + letra + "] ");
            }
            System.out.println();
        }
    }
}
 */





/*
import java.util.Arrays;

class Tablero {
    private String[][] tablero;

    public Tablero() {
        tablero = new String[15][15];
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " "); // Inicializa con un espacio en blanco como String
        }
    }

    public boolean colocarPalabra(String palabra, int fila, int col, boolean horizontal) {
        // Validar coordenadas
        if (fila < 0 || fila >= 15 || col < 0 || col >= 15) return false;

        if (horizontal) {
            if (col + palabra.length() > 15) return false;
            for (int i = 0; i < palabra.length(); i++) {
                if (!tablero[fila][col + i].equals(" ") && !tablero[fila][col + i].equals(String.valueOf(palabra.charAt(i)))) {
                    return false; // Colisión con otra letra
                }
            }
            for (int i = 0; i < palabra.length(); i++) {
                tablero[fila][col + i] = String.valueOf(palabra.charAt(i)); // Colocar la palabra
            }
        } else { // Vertical
            if (fila + palabra.length() > 15) return false;
            for (int i = 0; i < palabra.length(); i++) {
                if (!tablero[fila + i][col].equals(" ") && !tablero[fila + i][col].equals(String.valueOf(palabra.charAt(i)))) {
                    return false; // Colisión con otra letra
                }
            }
            for (int i = 0; i < palabra.length(); i++) {
                tablero[fila + i][col] = String.valueOf(palabra.charAt(i)); // Colocar la palabra
            }
        }
        return true;
    }

    public void colocarMultiplicadores() {
        // Ejemplo de colocación de un multiplicador
        tablero[0][0] = "3x"; // 3x
        tablero[1][1] = "2x"; // 2x
        // Puedes agregar más lógica para colocar otros multiplicadores
    }

    public void mostrarTablero() {
        System.out.print("   ");
        for (int col = 0; col < tablero[0].length; col++) {
            System.out.printf(" %2d ", col);
        }
        System.out.println();

        for (int fila = 0; fila < tablero.length; fila++) {
            System.out.printf("%2d  ", fila);
            for (String letra : tablero[fila]) {
                System.out.print("[" + letra + "] ");
            }
            System.out.println();
        }
    }

    public void limpiarTablero() {
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " "); // Reinicia cada celda a un espacio en blanco
        }
    }
}
 */

import java.util.Arrays;

class Tablero {
    private String[][] tablero;
    private static final String ROJO_FONDO = "\u001B[48;5;196m"; // Doble palabra
    private static final String TEXTO_NEGRO = "\u001B[30m"; // Texto negro
    private static final String AZUL_FONDO = "\u001B[48;5;33m"; // Triple palabra
    private static final String GRIS_CLARO_FONDO = "\u001B[48;5;250m"; // Fondo gris claro
    private static final String VERDE_FONDO = "\u001B[48;5;46m"; // Doble letra
    private static final String AMARILLO_FONDO = "\u001B[48;5;226m"; // Triple letra
    private static final String RESET = "\u001B[0m"; // Restablecer color

    public Tablero() {
        tablero = new String[15][15]; // Tablero de 15x15
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], " "); // Inicializa con un espacio en blanco como String
        }
        colocarMultiplicadores(); // Colocar multiplicadores en el tablero
    }

    public boolean colocarPalabra(String palabra, int fila, int col, boolean horizontal) {
        // Validar coordenadas
        if (fila < 0 || fila >= 15 || col < 0 || col >= 15) return false;

        if (horizontal) {
            if (col + palabra.length() > 15) return false;
            for (int i = 0; i < palabra.length(); i++) {
                if (!tablero[fila][col + i].equals(" ") && !tablero[fila][col + i].equals(String.valueOf(palabra.charAt(i)))) {
                    return false; // Colisión con otra letra
                }
            }
            for (int i = 0; i < palabra.length(); i++) {
                tablero[fila][col + i] = String.valueOf(palabra.charAt(i)); // Colocar la palabra
            }
        } else { // Vertical
            if (fila + palabra.length() > 15) return false;
            for (int i = 0; i < palabra.length(); i++) {
                if (!tablero[fila + i][col].equals(" ") && !tablero[fila + i][col].equals(String.valueOf(palabra.charAt(i)))) {
                    return false; // Colisión con otra letra
                }
            }
            for (int i = 0; i < palabra.length(); i++) {
                tablero[fila + i][col] = String.valueOf(palabra.charAt(i)); // Colocar la palabra
            }
        }
        return true;
    }

    public void colocarMultiplicadores() {
        // Doble palabra
        tablero[0][0] = "2"; tablero[0][7] = "2"; tablero[0][14] = "2";
        tablero[7][0] = "2"; tablero[7][14] = "2";
        tablero[14][0] = "2"; tablero[14][7] = "2"; tablero[14][14] = "2";

        // Triple palabra
        tablero[0][3] = "3"; tablero[0][11] = "3";
        tablero[3][0] = "3"; tablero[3][14] = "3";
        tablero[11][0] = "3"; tablero[11][14] = "3";
        tablero[14][3] = "3"; tablero[14][11] = "3";
        tablero[7][7] = "3"; // Centro

        // Doble letra
        tablero[1][5] = "2"; tablero[1][9] = "2";
        tablero[5][1] = "2"; tablero[5][5] = "2";
        tablero[5][9] = "2"; tablero[5][13] = "2";
        tablero[9][1] = "2"; tablero[9][5] = "2";
        tablero[9][9] = "2"; tablero[9][13] = "2";
        tablero[13][5] = "2"; tablero[13][9] = "2";

        // Triple letra
        tablero[1][1] = " 3"; tablero[1][13] = "3";
        tablero[13][1] = "3"; tablero[13][13] = "3";
        tablero[5][5] = "3"; tablero[5][9] = "3";
        tablero[9][5] = "3"; tablero[9][9] = "3";
    }

    public void mostrarTableroConColores() {
        // Imprimir encabezado de columnas
        System.out.print("    ");
        for (int col = 0; col < tablero[0].length; col++) {
            System.out.printf("|%s%3d%s", GRIS_CLARO_FONDO + TEXTO_NEGRO, col, RESET); // Coordenadas en gris con texto negro
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
    }

    public String centrarTexto(String texto, int ancho) {
        if (texto.trim().isEmpty()) {
            return " ".repeat(ancho);
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