import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase TableroManager proporciona métodos para guardar y cargar el estado de un juego
 * en archivos JSON utilizando la librería Gson.
 */
public class TableroManager {

    /**
     * Guarda el estado del juego (tablero y partida) en un archivo JSON.
     * @param alias el alias de la partida.
     * @param tablero el tablero a guardar.
     */
    public static void guardarJuego(String alias, Tablero tablero, Jugador jugador1, Jugador jugador2) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filename = "juego_" + alias + ".json";
        Partida partida = new Partida(alias, jugador1, jugador2, jugador1.getPuntajePartida(), false, 0, 0); // Cambiar aquí
        JuegoGuardado juegoGuardado = new JuegoGuardado(tablero, partida);
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(juegoGuardado, writer);
            System.out.println("Juego guardado exitosamente en " + filename);
        } catch (IOException e) {
            System.err.println("Error al guardar el juego: " + e.getMessage());
        }
    }

    /**
     * Carga el estado del juego (tablero y partida) desde un archivo JSON.
     * @param alias el alias de la partida.
     * @param saco el saco de letras utilizado en el juego.
     * @return el juego cargado o un juego vacío si ocurre un error.
     */
    public static JuegoGuardado cargarJuego(String alias, Saco saco) {
        Gson gson = new Gson();
        String filename = "juego_" + alias + ".json";
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, JuegoGuardado.class);
        } catch (IOException e) {
            System.err.println("Error al cargar el juego: " + e.getMessage());
            return new JuegoGuardado(new Tablero(saco), new Partida("", null, null, 0, false, 0, 0)); // Devuelve un juego vacío si hay un error
        }
    }
}