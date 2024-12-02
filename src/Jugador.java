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
        for (String letra : letras) {
            letrasEnMano.put(letra, letrasEnMano.getOrDefault(letra, 0) + 1);
        }
        for (char c : palabra.toCharArray()) {
            String letra = String.valueOf(c).toUpperCase();
            if (!letrasEnMano.containsKey(letra) || letrasEnMano.get(letra) <= 0) {
                return false;
            }
            letrasEnMano.put(letra, letrasEnMano.get(letra) - 1);
        }
        return true;
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