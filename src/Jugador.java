public class Jugador {
    private String correo;
    private String alias;
    private int victorias;

    public Jugador(String correo, String alias) {
        this.correo = correo;
        this.alias = alias;
        this.victorias = 0;
    }

    public String getCorreo() {
        return correo;
    }

    public String getAlias() {
        return alias;
    }
}
