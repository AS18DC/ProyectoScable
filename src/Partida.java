/**
 * Clase que representa una partida de Scrabble.
 */
public class Partida {
    private String alias;
    private boolean gano;
    private int puntos;
    private long tiempoTotal;
    private int palabrasColocadas;

    /**
     * Constructor de la clase Partida.
     *
     * @param alias El alias del jugador.
     * @param puntos Los puntos obtenidos en la partida.
     * @param gano Indica si el jugador ganó la partida.
     * @param tiempoTotal El tiempo total de la partida en segundos.
     * @param palabrasColocadas El número de palabras colocadas durante la partida.
     */
    public Partida(String alias, int puntos, boolean gano, long tiempoTotal, int palabrasColocadas) {
        this.alias = alias;
        this.puntos = puntos;
        this.gano = gano;
        this.tiempoTotal = tiempoTotal;
        this.palabrasColocadas = palabrasColocadas;
    }

    /**
     * Obtiene el alias del jugador.
     *
     * @return El alias del jugador.
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * Establece el alias del jugador.
     *
     * @param alias El nuevo alias del jugador.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Verifica si el jugador ganó la partida.
     *
     * @return true si el jugador ganó la partida, false en caso contrario.
     */
    public boolean isGano() {
        return this.gano;
    }

    /**
     * Establece si el jugador ganó la partida.
     *
     * @param gano true si el jugador ganó la partida, false en caso contrario.
     */
    public void setGano(boolean gano) {
        this.gano = gano;
    }

    /**
     * Obtiene los puntos obtenidos en la partida.
     *
     * @return Los puntos obtenidos en la partida.
     */
    public int getPuntos() {
        return this.puntos;
    }

    /**
     * Establece los puntos obtenidos en la partida.
     *
     * @param puntos Los puntos obtenidos en la partida.
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Obtiene el tiempo total de la partida en segundos.
     *
     * @return El tiempo total de la partida en segundos.
     */
    public long getTiempoTotal() {
        return this.tiempoTotal;
    }

    /**
     * Establece el tiempo total de la partida en segundos.
     *
     * @param tiempoTotal El tiempo total de la partida en segundos.
     */
    public void setTiempoTotal(long tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    /**
     * Obtiene el número de palabras colocadas durante la partida.
     *
     * @return El número de palabras colocadas durante la partida.
     */
    public int getPalabrasColocadas() {
        return this.palabrasColocadas;
    }

    /**
     * Establece el número de palabras colocadas durante la partida.
     *
     * @param palabrasColocadas El número de palabras colocadas durante la partida.
     */
    public void setPalabrasColocadas(int palabrasColocadas) {
        this.palabrasColocadas = palabrasColocadas;
    }
}
