import java.util.List;

public class JuegoGuardado {
    private Tablero tablero;
    private Partida partida;
    private List<String> letrasJugador1;
    private List<String> letrasJugador2;
    private int puntajeJugador1;
    private int puntajeJugador2;
    private int contadorMovimientos;

    public JuegoGuardado(Tablero tablero, Partida partida, List<String> letrasJugador1, List<String> letrasJugador2, int puntajeJugador1, int puntajeJugador2, int contadorMovimientos) {
        this.tablero = tablero;
        this.partida = partida;
        this.letrasJugador1 = letrasJugador1;
        this.letrasJugador2 = letrasJugador2;
        this.puntajeJugador1 = puntajeJugador1;
        this.puntajeJugador2 = puntajeJugador2;
        this.contadorMovimientos = contadorMovimientos;
    }

    public Tablero getTablero() { return tablero; }
    public Partida getPartida() { return partida; }
    public List<String> getLetrasJugador1() { return letrasJugador1; }
    public List<String> getLetrasJugador2() { return letrasJugador2; }
    public int getPuntajeJugador1() { return puntajeJugador1; }
    public int getPuntajeJugador2() { return puntajeJugador2; }
    public int getContadorMovimientos() {
        return contadorMovimientos;
    }

    public void setContadorMovimientos(int contadorMovimientos) {
        this.contadorMovimientos = contadorMovimientos;
    }
}
