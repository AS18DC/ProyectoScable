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

    public void agregarLetra(String letra) {
        for (Letra l : letras) {
            if (l.letra.equals(letra)) {
                l.cantidad++; // Incrementar la cantidad de la letra
                return;
            }
        }
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

        // Repartir letras hasta alcanzar la cantidad deseada o hasta que se agoten las letras
        while (letrasRepartidas < cantidad && !letras.isEmpty()) {
            int index = rand.nextInt(letras.size());
            Letra letra = letras.get(index);

            // Verificar si hay letras disponibles
            if (letra.cantidad > 0) {
                // Agregar la letra al jugador
                jugador.agregarLetra(letra.letra, letra.puntaje);
                letra.cantidad--;
                letrasRepartidas++;
            }

            // Si la letra se ha agotado, eliminarla de la lista
            if (letra.cantidad == 0) {
                letras.remove(index);
            }
        }

        // Mensaje si no se pudieron repartir todas las letras solicitadas
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


    public boolean cambiarFichas(Jugador jugador) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar las letras que tiene el jugador
        System.out.println("Letras disponibles del jugador: " + jugador.getLetras());

        // Lista para almacenar las letras a cambiar
        List<String> letrasACambiar = new ArrayList<>();
        String letraACambiar;

        // Preguntar al usuario qué letras quiere cambiar
        while (true) {
            System.out.println("Ingrese la letra a cambiar (o 'salir' para terminar): ");
            letraACambiar = scanner.nextLine();

            if (letraACambiar.equalsIgnoreCase("salir")) {
                break; // Salir del bucle si el usuario escribe 'salir'
            }

            // Verificar si el jugador tiene la letra
            if (jugador.getLetras().contains(letraACambiar)) {
                letrasACambiar.add(letraACambiar);
            } else {
                System.out.println("El jugador no tiene la letra: " + letraACambiar);
            }
        }

        // Verificar si hay letras disponibles en el saco
        if (letras.isEmpty()) {
            System.out.println("No hay letras disponibles en el saco para cambiar.");
            return false; // No se puede realizar el cambio
        }

        // Cambiar las letras seleccionadas
        for (String letra : letrasACambiar) {
            // Devolver la letra al saco
            agregarLetra(letra);

            // Quitar la letra del jugador
            jugador.quitarLetra(letra);

            // Obtener una letra aleatoria del saco
            Random random = new Random();
            Letra letraAleatoria = letras.get(random.nextInt(letras.size()));

            // Agregar la nueva letra al jugador
            jugador.agregarLetra(letraAleatoria.letra, letraAleatoria.puntaje);

            // Decrementar la cantidad de la letra aleatoria en el saco
            letraAleatoria.cantidad--;

            // Si la letra aleatoria se ha agotado, eliminarla del saco
            if (letraAleatoria.cantidad == 0) {
                letras.remove(letraAleatoria);
            }

            System.out.println("Cambio realizado: " + letra + " por " + letraAleatoria.letra);
        }

        return true;
    }


    public int contarLetrasEnSaco() {
        int totalLetras = 0;

        for (Letra letra : letras) {
            totalLetras += letra.cantidad; // Sumar la cantidad de cada letra
        }

        return totalLetras; // Retornar el total
    }
}