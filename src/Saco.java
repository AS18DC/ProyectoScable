import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Saco {
    private List<Letra> letras;

    public Saco() {
        letras = new ArrayList<>();
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
        letras.add(new Letra("X", 10, 1));
        letras.add(new Letra("Y", 5, 1));
        letras.add(new Letra("Z", 10, 1));
        letras.add(new Letra("-", 0, 2));

    }

    public List<Letra> getLetras() {
        return letras;
    }


    public void quitarLetrasJugador(Jugador jugador) {
        jugador.getLetras().clear();
    }


    public void devolverLetrasAlSaco(Jugador jugador) {
        List<String> letrasJugador = jugador.getLetras(); // Obtener las letras del jugador

        // Iterar sobre las letras del jugador
        for (String letra : letrasJugador) {
            // Buscar la letra en el saco
            for (Letra letraSaco : letras) {
                if (letraSaco.letra.equals(letra)) {
                    letraSaco.cantidad++; // Aumentar la cantidad en el saco
                    break;
                }
            }
        }
        quitarLetrasJugador(jugador);
    }

    public void repartirLetras(Jugador jugador, int cantidad) {
        Random rand = new Random();
        int letrasRepartidas = 0;

        // Calcular cuántas letras realmente necesita para llegar a 7
        int letrasFaltantes = 7 - jugador.getLetras().size();
        cantidad = Math.min(cantidad, letrasFaltantes);

        while (letrasRepartidas < cantidad && !letras.isEmpty()) {
            int index = rand.nextInt(letras.size());
            Letra letra = letras.get(index);

            if (letra.cantidad > 0) {
                jugador.agregarLetra(letra.letra, letra.puntaje);
                letra.cantidad--;
                letrasRepartidas++;
            }
        }

        if (letrasRepartidas < cantidad) {
            System.out.println("No hay suficientes letras disponibles en el saco para repartir " + cantidad + " letras.");
        }
    }


    public boolean letraMasCercanaA(List<String> letra1, List<String> letra2) {
        String primer = letra1.get(0);
        String segundo = letra2.get(0);
        char primerCaracter1 = primer.charAt(0);
        char primerCaracter2 = segundo.charAt(0);
        int distancia1 = Math.abs(primerCaracter1 - 'A');
        int distancia2 = Math.abs(primerCaracter2 - 'A');

        char Ñ = 13;
        if (primerCaracter1 == Ñ) {
            distancia1 = Ñ;
        } else if (primerCaracter2 == Ñ) {
            distancia2 = Ñ;
        }

        if ((primerCaracter1 == '-') || (distancia1 < distancia2)) {
            return true;
        } else if ((primerCaracter2 == '-') || (distancia2 < distancia1)) {
            return false;
        } else {
            System.out.println("Las dos letras son iguales se va a volver a sortear: ");
            return true;
        }

    }


    public boolean cambiarFicha(Jugador jugador, String letraACambiar) {
        Scanner scanner = new Scanner(System.in);

        // Verificar si el jugador tiene la letra a cambiar
        while (!jugador.getLetras().contains(letraACambiar)) {
            System.out.println("El jugador no tiene la letra: " + letraACambiar);
            System.out.println("Coloque la letra a cambiar: ");
            letraACambiar = scanner.nextLine();
        }

        // Verificar si hay letras disponibles en el saco
        if (letras.isEmpty()) {
            System.out.println("No hay letras disponibles en el saco para cambiar.");
            return false; // No se puede realizar el cambio
        }

        // Seleccionar una letra aleatoria del saco
        Random random = new Random();
        Letra letraAleatoria = letras.get(random.nextInt(letras.size()));

        // Cambiar solo una instancia de la letra
        jugador.quitarLetra(letraACambiar); // Esto debe quitar solo una instancia de la letra
        jugador.agregarLetra(letraAleatoria.letra, letraAleatoria.puntaje);

        System.out.println("Cambio realizado: " + letraACambiar + " por " + letraAleatoria.letra);
        return true;
    }
}