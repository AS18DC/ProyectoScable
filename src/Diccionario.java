import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Diccionario {
    private HashSet<String> palabras;

    public Diccionario(String rutaArchivo) throws IOException {
        palabras = new HashSet<>();
        cargarDiccionario(rutaArchivo);
    }

    private void cargarDiccionario(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea.trim().toLowerCase());
            }
        }
    }

    public boolean existePalabra(String palabra) {
        return palabras.contains(palabra.toLowerCase());
    }
}