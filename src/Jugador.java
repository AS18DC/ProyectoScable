import java.util.*;

class Jugador {
    private String nombre;
    private String correoElectronico;
    private int puntaje;
    private int puntajePartida;
    private List<String> letras;

    public Jugador(String correoElectronico, String nombre) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.puntaje = 0;
        this.letras = new ArrayList<String>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntajePartida() {
        return puntajePartida;
    }

    public void setPuntajePartida(int puntajePartida) {
        this.puntajePartida = puntajePartida;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getLetras() {
        return letras;
    }
    public void setLetras(List<String> letras) { this.letras = letras; }

    public void agregarLetra(String letra, int puntaje) {
        if (letras.size() < 7) {
            letras.add(letra);
            this.puntaje += puntaje;
        }
    }

    public boolean quitarLetra(String letra) {
        return letras.remove(letra);
    }


    public boolean verificarLetrasParaPalabra(String palabra, Tablero tablero) {
        Map<String, Integer> letrasEnMano = new HashMap<>();
        for (String letra : letras) {
            letrasEnMano.put(letra, letrasEnMano.getOrDefault(letra, 0) + 1);
        }

        Map<String, Integer> letrasEnTablero = new HashMap<>();
        String[][] matrizTablero = tablero.getTablero();

        for (int fila = 0; fila < matrizTablero.length; fila++) {
            for (int col = 0; col < matrizTablero[fila].length; col++) {
                String letraEnTablero = matrizTablero[fila][col].trim();
                if (!letraEnTablero.isEmpty() && !letraEnTablero.contains("XP") && !letraEnTablero.contains("XL")) {
                    letrasEnTablero.put(letraEnTablero, letrasEnTablero.getOrDefault(letraEnTablero, 0) + 1);
                }
            }
        }

        for (char c : palabra.toUpperCase().toCharArray()) {
            String letra = String.valueOf(c);

            if (letrasEnMano.getOrDefault(letra, 0) > 0) {
                letrasEnMano.put(letra, letrasEnMano.get(letra) - 1);
            } else if (letrasEnTablero.getOrDefault(letra, 0) > 0) {
                letrasEnTablero.put(letra, letrasEnTablero.get(letra) - 1);
            } else {
                return false;
            }
        }

        return true;
    }


    public void usarLetras(List<String> usadas) {
        for (String letra : usadas) {
            // Eliminar la letra del jugador
            if (letras.contains(letra)) {
                letras.remove(letra); // Elimina solo la primera coincidencia
            }
        }

    }
}
