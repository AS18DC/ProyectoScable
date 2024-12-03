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
     * Guarda el estado del juego (tablero, jugadores y saco) en un archivo JSON.
     * @param alias el alias de la partida.
     * @param tablero el tablero a guardar.
     * @param jugador1 el primer jugador.
     * @param jugador2 el segundo jugador.
     * @param saco el saco de letras utilizado en el juego.
     */
    public static void guardarJuego(String alias, Tablero tablero, Jugador jugador1, Jugador jugador2, Saco saco) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filename = "juego_" + alias + ".json";
        Partida partida = new Partida(alias, jugador1, jugador2, saco, jugador1.getPuntajePartida(), false, 0, 0);
        JuegoGuardado juegoGuardado = new JuegoGuardado(tablero, partida);
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(juegoGuardado, writer);
            System.out.println("Juego guardado exitosamente en " + filename);
        } catch (IOException e) {
            System.err.println("Error al guardar el juego: " + e.getMessage());
        }
    }

    /**
     * Carga el estado del juego (tablero, jugadores y saco) desde un archivo JSON.
     * @param alias el alias de la partida.
     * @return el juego cargado o un juego vacío si ocurre un error.
     */
    public static JuegoGuardado cargarJuego(String alias) {
        Gson gson = new Gson();
        String filename = "juego_" + alias + ".json";
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, JuegoGuardado.class);
        } catch (IOException e) {
            System.err.println("Error al cargar el juego: " + e.getMessage());
            return new JuegoGuardado(new Tablero(new Saco()), new Partida("", null, null, new Saco(), 0, false, 0, 0));
        }
    }
}