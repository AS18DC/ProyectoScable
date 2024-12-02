import java.util.*;

class Jugador {
    private String nombre;
    private String correoElectronico;
    private int puntaje;
    private int puntajePartida;
    private List<String> letras;

    public Jugador(String correoElectronico, String nombre) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.puntaje = 0;
        this.letras = new ArrayList<String>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntajePartida() {
        return puntajePartida;
    }

    public void setPuntajePartida(int puntajePartida) {
        this.puntajePartida = puntajePartida;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getLetras() {
        return letras;
    }

    public void agregarLetra(String letra, int puntaje) {
        if (letras.size() < 7) {
            letras.add(letra);
            this.puntaje += puntaje;
        }
    }

    public boolean quitarLetra(String letra) {
        return letras.remove(letra);
    }


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

    public void usarLetras(List<String> usadas) {
        for (String letra : usadas) {
            // Eliminar la letra del jugador
            if (letras.contains(letra)) {
                letras.remove(letra); // Elimina solo la primera coincidencia
            }
        }

    }
}
