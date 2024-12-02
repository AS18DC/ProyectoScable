import java.util.*;

/**
 * Clase que representa a un jugador en el juego de Scrabble.
 */
class Jugador {
    private String nombre;
    private String correoElectronico;
    private int puntaje;
    private int puntajePartida;
    private List<String> letras;

    /**
     * Constructor de la clase Jugador.
     *
     * @param correoElectronico El correo electrónico del jugador.
     * @param nombre El nombre del jugador.
     */
    public Jugador(String correoElectronico, String nombre) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.puntaje = 0;
        this.letras = new ArrayList<String>();
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el puntaje de la partida actual del jugador.
     *
     * @return El puntaje de la partida actual.
     */
    public int getPuntajePartida() {
        return puntajePartida;
    }

    /**
     * Establece el puntaje de la partida actual del jugador.
     *
     * @param puntajePartida El puntaje de la partida actual.
     */
    public void setPuntajePartida(int puntajePartida) {
        this.puntajePartida = puntajePartida;
    }

    /**
     * Obtiene el puntaje total del jugador.
     *
     * @return El puntaje total del jugador.
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Obtiene el correo electrónico del jugador.
     *
     * @return El correo electrónico del jugador.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del jugador.
     *
     * @param correoElectronico El nuevo correo electrónico del jugador.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre El nuevo nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene las letras actuales del jugador.
     *
     * @return La lista de letras del jugador.
     */
    public List<String> getLetras() {
        return letras;
    }

    /**
     * Agrega una letra a la mano del jugador y actualiza su puntaje.
     *
     * @param letra La letra a agregar.
     * @param puntaje El puntaje de la letra.
     */
    public void agregarLetra(String letra, int puntaje) {
        if (letras.size() < 7) {
            letras.add(letra);
            this.puntaje += puntaje;
        }
    }

    /**
     * Quita una letra de la mano del jugador.
     *
     * @param letra La letra a quitar.
     * @return true si la letra fue removida, false en caso contrario.
     */
    public boolean quitarLetra(String letra) {
        return letras.remove(letra);
    }

    /**
     * Verifica si el jugador tiene las letras necesarias para formar una palabra.
     *
     * @param palabra La palabra a verificar.
     * @return true si el jugador tiene las letras necesarias, false en caso contrario.
     */
    public boolean verificarLetrasParaPalabra(String palabra) {
        Map<String, Integer> letrasEnMano = new HashMap<>();

        // Contamos las letras disponibles
        for (String letra : letras) {
            letrasEnMano.put(letra, letrasEnMano.getOrDefault(letra, 0) + 1);
        }

        // Verificamos si la palabra puede ser formada
        int i = 0;
        while (i < palabra.length()) {
            String letraActual = String.valueOf(palabra.charAt(i)).toUpperCase();
            String letraConjunto = null;

            // Verificamos si hay un conjunto de dos letras
            if (i < palabra.length() - 1) {
                String posibleConjunto = palabra.substring(i, i + 2).toUpperCase();
                if (letrasEnMano.containsKey(posibleConjunto) && letrasEnMano.get(posibleConjunto) > 0) {
                    letraConjunto = posibleConjunto;
                    i += 2; // Saltamos el siguiente carácter
                }
            }

            // Si no encontramos un conjunto, verificamos la letra individual
            if (letraConjunto == null) {
                if (!letrasEnMano.containsKey(letraActual) || letrasEnMano.get(letraActual) <= 0) {
                    return false; // No se puede formar la palabra
                }
                letrasEnMano.put(letraActual, letrasEnMano.get(letraActual) - 1);
                i++; // Avanzamos al siguiente carácter
            } else {
                // Si encontramos un conjunto, lo restamos del mapa
                letrasEnMano.put(letraConjunto, letrasEnMano.get(letraConjunto) - 1);
            }
        }
        return true; // La palabra puede ser formada
    }

    /**
     * Marca las letras usadas por el jugador, eliminándolas de su mano.
     *
     * @param usadas La lista de letras usadas.
     */
    public void usarLetras(List<String> usadas) {
        for (String letra : usadas) {
            if (letras.contains(letra)) {
                letras.remove(letra); // Elimina solo la primera coincidencia
            }
        }
    }
}
