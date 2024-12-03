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
        List<String> letrasJugador = new ArrayList<>(jugador.getLetras());
        for (String letra : letrasJugador) {
            boolean letraEncontrada = false;
            for (Letra letraSaco : letras) {
                if (letraSaco.letra.equals(letra)) {
                    letraSaco.cantidad++; // Incrementa la cantidad independientemente de su valor actual
                    letraEncontrada = true;
                    break;
                }
            }

            // Si la letra no se encontró, significa que probablemente ya no estaba en el saco
            if (!letraEncontrada) {
                // Volver a agregar la letra al saco
                letras.add(new Letra(letra, obtenerPuntajeDeLaLetra(letra), 1));
            }
        }
        quitarLetrasJugador(jugador);
    }

    public int obtenerPuntajeDeLaLetra(String letra) {
        switch (letra) {
            case "A": return 1;
            case "B": return 4;
            case "C": return 3;
            case "CH": return 8;
            case "D": return 3;
            case "E": return 1;
            case "F": return 5;
            case "G": return 3;
            case "H": return 5;
            case "I": return 1;
            case "J": return 10;
            case "L": return 2;
            case "LL": return 8;
            case "M": return 3;
            case "N": return 2;
            case "Ñ": return 10;
            case "O": return 1;
            case "P": return 4;
            case "Q": return 8;
            case "R": return 2;
            case "RR": return 8;
            case "S": return 1;
            case "T": return 2;
            case "U": return 1;
            case "V": return 4;
            case "X": return 10;
            case "Y": return 5;
            case "Z": return 10;
            case "-": return 0;
            default: return 1; // Valor por defecto para cualquier letra no reconocida
        }
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

        System.out.println("Letras disponibles del jugador: " + jugador.getLetras());

        List<String> letrasACambiar = new ArrayList<>();
        String letraACambiar;

        while (true) {
            System.out.println("Ingrese la letra a cambiar (o 'salir' para terminar): ");
            letraACambiar = scanner.nextLine();

            if (letraACambiar.equalsIgnoreCase("salir")) {
                break;
            }

            if (jugador.getLetras().contains(letraACambiar)) {
                letrasACambiar.add(letraACambiar);
            } else {
                System.out.println("El jugador no tiene la letra: " + letraACambiar);
            }
        }

        if (letras.isEmpty()) {
            System.out.println("No hay letras disponibles en el saco para cambiar.");
            return false; // No se puede realizar el cambio
        }

        // Cambiar las letras seleccionadas
        for (String letra : letrasACambiar) {
            // Devolver la letra al saco
            boolean letraEncontrada = false;
            for (Letra letraSaco : letras) {
                if (letraSaco.letra.equals(letra)) {
                    letraSaco.cantidad++; // Incrementa la cantidad
                    letraEncontrada = true;
                    break;
                }
            }

            // Si la letra no se encontró en el saco, agregarla
            if (!letraEncontrada) {
                letras.add(new Letra(letra, obtenerPuntajeDeLaLetra(letra), 1));
            }

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
