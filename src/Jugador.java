import java.util.ArrayList;
import java.util.List;

class Jugador {
    private String nombre;
    private int puntaje;
    private List<String> letras;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.letras = new ArrayList<String>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public List<String> getLetras() {
        return letras;
    }

    public void agregarLetra(String letra, int puntaje) {
        letras.add(letra);
        this.puntaje += puntaje;
    }

    public boolean quitarLetra(String letra) {
        return letras.remove(letra);
    }

    public void usarLetras(List<Character> usadas) {
        letras.removeAll(usadas);
    }
}