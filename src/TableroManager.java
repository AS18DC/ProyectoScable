import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase TableroManager proporciona métodos para guardar y cargar el estado de un tablero
 * en archivos JSON utilizando la librería Gson.
 */
public class TableroManager {

    /**
     * Guarda el estado del tablero asociado a una partida en un archivo JSON.
     * @param alias el alias de la partida.
     * @param tablero el tablero a guardar.
     */
    public static void guardarTablero(String alias, Tablero tablero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filename = "tablero_" + alias + ".json";
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(tablero, writer);
            System.out.println("Tablero guardado exitosamente en " + filename);
        } catch (IOException e) {
            System.err.println("Error al guardar el tablero: " + e.getMessage());
        }
    }

    /**
     * Carga el estado del tablero asociado a una partida desde un archivo JSON.
     * @param alias el alias de la partida.
     * @param saco el saco de letras utilizado en el juego.
     * @return el tablero cargado o un tablero vacío si ocurre un error.
     */
    public static Tablero cargarTablero(String alias, Saco saco) {
        Gson gson = new Gson();
        String filename = "tablero_" + alias + ".json";
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, Tablero.class);
        } catch (IOException e) {
            System.err.println("Error al cargar el tablero: " + e.getMessage());
            return new Tablero(saco); // Devuelve un tablero vacío si hay un error
        }
    }
}
