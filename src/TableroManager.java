import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TableroManager {

    // Guardar el tablero asociado a una partida
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

    // Cargar el tablero asociado a una partida
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
