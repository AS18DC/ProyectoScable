import java.util.Arrays;

class Tablero {
    private char[][] tablero;

    public Tablero() {
        tablero = new char[15][15];
        for (int i = 0; i < 15; i++) {
            Arrays.fill(tablero[i], ' ');
        }
    }

    /*
    public void displayBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print("[" + tablero[i][j] + "]");
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }
    */

    public boolean colocarPalabra(String palabra, int fila, int col, boolean horizontal) {
        if (horizontal) {
            if (col + palabra.length() > 15) return false;
            for (int i = 0; i < palabra.length(); ) ;
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

    public void mostrarTablero() {
        for (char[] fila : tablero) {
            for (char letra : fila) {
                System.out.print("[" + letra + "]");
            }
            System.out.println();
        }
    }
}