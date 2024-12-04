public class JuegoGuardado {
    private Tablero tablero;
    private Partida partida;

    public JuegoGuardado(Tablero tablero, Partida partida) {
        this.tablero = tablero;
        this.partida = partida;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
