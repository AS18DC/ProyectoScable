import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Saco {
    private List<Letra> letras;

    public Saco() {
        letras = new ArrayList<>();
        // Inicializar el saco con letras y sus puntajes
        letras.add(new Letra("A", 1, 12));
        letras.add(new Letra("B", 4, 2));
        letras.add(new Letra("C", 3, 4));
        letras.add(new Letra("CH", 8, 1));
        letras.add(new Letra("D", 3, 5));
        letras.add(new Letra("E", 1, 12));
        letras.add(new Letra("F", 5, 1));
        letras.add(new Letra("G", 3, 2));
        letras.add(new Letra("H", 5, 2));
        letras.add(new Letra("I", 1, 6));
        letras.add(new Letra("J", 10, 1));
        //letras.add(new Letra("K", 2, 4));
        letras.add(new Letra("L", 2, 4));
        letras.add(new Letra("LL", 8, 1));
        letras.add(new Letra("M", 3, 2));
        letras.add(new Letra("N", 2, 5));
        letras.add(new Letra("Ñ", 10, 1));
        letras.add(new Letra("O", 1, 9));
        letras.add(new Letra("P", 4, 2));
        letras.add(new Letra("Q", 8, 1));
        letras.add(new Letra("R", 2, 5));
        letras.add(new Letra("RR", 8, 1));
        letras.add(new Letra("S", 1, 6));
        letras.add(new Letra("T", 2, 4));
        letras.add(new Letra("U", 1, 5));
        letras.add(new Letra("V", 4, 1));
        //letras.add(new Letra("W", 4, 2));
        letras.add(new Letra("X", 10, 1));
        letras.add(new Letra("Y", 5, 1));
        letras.add(new Letra("Z", 10, 1));
        letras.add(new Letra(" ", 2, 0));

    }

    public List<Letra> getLetras() {
        return letras;
    }

    public void repartirLetras(Jugador jugador) {
        Random rand = new Random();
        while (jugador.getLetras().size() < 7 && !letras.isEmpty()) {
            int index = rand.nextInt(letras.size());
            Letra letra = letras.get(index);
            if (letra.cantidad > 0) {
                jugador.agregarLetra(letra.letra, letra.puntaje);
                letra.cantidad--;
            }
        }
    }
}