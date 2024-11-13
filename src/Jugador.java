import java.util.*;

class Jugador {
    private String nombre;
    private String correoElectronico;
    private int puntaje;
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
        letras.add(letra);
        this.puntaje += puntaje;
    }

    public boolean quitarLetra(String letra) {
        return letras.remove(letra);
    }


    public boolean verificarLetrasParaPalabra(String palabra) {
        // Crear un mapa para contar las letras en la mano del jugador
        Map<String, Integer> letrasEnMano = new HashMap<>();

        // Contar las letras en la mano
        for (String letra : letras) {
            letrasEnMano.put(letra, letrasEnMano.getOrDefault(letra, 0) + 1);
        }

        // Verificar si el jugador tiene las letras necesarias para la palabra
        ///cambiarlo a que lea en String
        ///hacer funcion para leer los CH, LL, RR
        for (char c : palabra.toCharArray()) {
            String letra = String.valueOf(c).toUpperCase(); // Asegurarse de que la letra sea mayúscula
            if (!letrasEnMano.containsKey(letra) || letrasEnMano.get(letra) <= 0) {
                return false; // No tiene la letra necesaria
            }
            // Decrementar el conteo de la letra utilizada
            letrasEnMano.put(letra, letrasEnMano.get(letra) - 1);
        }

        return true; // Todas las letras están disponibles
    }

    public void usarLetras(List<Character> usadas) {
        letras.removeAll(usadas);
    }
}