import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;


public class GestionListaJSON {
    private static final String FileJugadores = "jugadores.json";
    private static final String FilePartidas = "partidas.json";

    public static LinkedList<Jugador> leerJugadoresExistentes() {
        File archivo = new File(FileJugadores);

        if (!archivo.exists()) {
            return new LinkedList<>();
        }

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FileJugadores)) {
            Type tipoLista = new TypeToken<LinkedList<Jugador>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.err.println("Error al leer los jugadores existentes: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    // Guardar jugadores en el archivo JSON
    public static void guardarJugadores(LinkedList<Jugador> jugadores) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FileJugadores)) {
            gson.toJson(jugadores, writer);
            System.out.println("Lista actualizada guardada exitosamente en " + FileJugadores);
        } catch (IOException e) {
            System.err.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }

    public static LinkedList<Partida> leerPartidasExistentes() {
        File archivo = new File(FilePartidas);

        if (!archivo.exists()) {
            return new LinkedList<>();
        }

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FilePartidas)) {
            Type tipoLista = new TypeToken<LinkedList<Jugador>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.err.println("Error al leer las partidas existentes: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    public static void guardarPartidas(LinkedList<Partida> partidas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FilePartidas)) {
            gson.toJson(partidas, writer);
            System.out.println("Lista actualizada guardada exitosamente en " + FilePartidas);
        } catch (IOException e) {
            System.err.println("Error al guardar las partidas: " + e.getMessage());
        }
    }

}
