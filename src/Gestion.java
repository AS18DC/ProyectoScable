import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Gestion {

    private LinkedList<Jugador> jugadores;
    private LinkedList<Partida> partidas;

    public Gestion() {
        jugadores = new LinkedList<Jugador>();
        partidas = new LinkedList<Partida>();
        this.jugadores = GestionListaJSON.leerJugadoresExistentes();
    }

    public void agregarPartida(Partida partida) {
        partidas.add(partida);
        System.out.println("Partida registrada con éxito.");
        guardarPartidasEnArchivo();
    }

    public void guardarPartidasEnArchivo() {
        try (FileWriter file = new FileWriter("partidas.json", true)) {
            if (new File("partidas.json").length() > 2) {
                file.write("[\n");
            }
            for (int i = 0; i < partidas.size(); i++) {
                Partida partida = partidas.get(i);
                file.write("{\n" +
                        "  \"alias\": \"" + partida.getAlias() + "\",\n" +
                        "  \"gano\": " + partida.isGano() + ",\n" +
                        "  \"puntos\": " + partida.getPuntos() + ",\n" +
                        "  \"tiempoTotal\": " + partida.getTiempoTotal() + ",\n" +
                        "  \"palabrasColocadas\": " + partida.getPalabrasColocadas() + "\n" +
                        "}");
                if (i < partidas.size() - 1) {
                    file.write(",\n");
                }
            }
            file.write("\n]");
            System.out.println("Partidas guardadas correctamente en partidas.json.");
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    public void leerPartidasEnArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("partidas.json"))) {
            Gson gson = new Gson();
            Type partidaListType = new TypeToken<List<Partida>>() {}.getType();
            List<Partida> partidaList = gson.fromJson(reader, partidaListType);
            if (partidaList != null) {
                partidas.clear();
                partidas.addAll(partidaList);
                System.out.println("Partidas cargadas desde el archivo correctamente.");
            } else {
                System.out.println("No se encontraron partidas en el archivo.");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void mostrarEstadisticasDePartidas(String aliasJugador) {
        int partidasJugadas = 0;
        int partidasGanadas = 0;
        int totalPuntos = 0;
        long totalTiempo = 0;
        int totalPalabrasColocadas = 0;

        for (Partida partida : partidas) {
            if (partida.getAlias().equalsIgnoreCase(aliasJugador)) {
                partidasJugadas++;
                if (partida.isGano()) {
                    partidasGanadas++;
                }
                totalPuntos += partida.getPuntos();
                totalTiempo += partida.getTiempoTotal();
                totalPalabrasColocadas += partida.getPalabrasColocadas();
            }
        }

        if (partidasJugadas > 0) {
            System.out.println("Estadísticas de las partidas para el jugador \"" + aliasJugador + "\":");
            System.out.println("Total de partidas jugadas: " + partidasJugadas);
            System.out.println("Total de partidas ganadas: " + partidasGanadas);
            System.out.println("Total de puntos: " + totalPuntos);
            System.out.println("Total de tiempo jugado (en segundos): " + totalTiempo);
            System.out.println("Total de palabras colocadas: " + totalPalabrasColocadas);
        } else {
            System.out.println("No se encontraron partidas para el jugador \"" + aliasJugador + "\".");
        }
    }

    public void registrarJugador(String correo, String alias) {
        jugadores.add(new Jugador(correo, alias));
    }

    public Jugador consultarJugador(String alias) {
        GestionListaJSON.leerJugadoresExistentes();
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(alias)) {
                return jugador;
            }
        }
        System.out.println("El jugador no se encuentra en el archivo de registros. Intente con otro alias.");
        return null;
    }

    public void editarCorreo(String alias, String nuevoCorreo) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(alias)) {
                jugador.setCorreoElectronico(nuevoCorreo);
                System.out.println("El correo electrónico del jugador " + alias + " ha sido actualizado.");
                return;
            }
        }
        System.out.println("Jugador con alias \"" + alias + "\" no encontrado.");
    }

    public void editarAlias(String alias, String nuevoAlias) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(alias)) {
                jugador.setNombre(nuevoAlias);
                System.out.println("El alias del jugador ha sido actualizado a " + nuevoAlias + ".");
                return;
            }
        }
        System.out.println("Jugador con alias \"" + alias + "\" no encontrado.");
    }

    public void validarJugador(String nombre, String correoElectronico) throws JugadorInvalido {
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() < 3) {
            throw new JugadorInvalido("Nombre inválido: debe tener al menos 3 caracteres.");
        }
        if (correoElectronico == null || !correoElectronico.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new JugadorInvalido("Email inválido: " + correoElectronico);
        }
    }

    public void menuRegistro(Gestion gestionJugador) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú de opciones ---");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Consultar jugador");
            System.out.println("3. Editar correo de un jugador");
            System.out.println("4. Editar alias de un jugador");
            System.out.println("5. Registrar partida");
            System.out.println("6. Mostrar estadísticas de partidas");
            System.out.println("7. Volver a menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.print("Introduce el correo del jugador: ");
                String correo = scanner.nextLine();
                System.out.print("Introduce el alias del jugador: ");
                String alias = scanner.nextLine();
                boolean validplayer= false;

                while (validplayer){
                    try {
                        validarJugador(alias,correo);
                        validplayer = true;
                    } catch (JugadorInvalido e) {
                        throw new RuntimeException();
                    }
                }
                registrarJugador(correo, alias);
                GestionListaJSON.leerJugadoresExistentes();
                GestionListaJSON.guardarJugadores(jugadores);

            } else if (opcion == 2) {
                System.out.print("Introduce el alias del jugador que deseas consultar: ");
                String aliasConsulta = scanner.nextLine();
                Jugador jugador = gestionJugador.consultarJugador(aliasConsulta);

                if (jugador != null) {
                    System.out.println("Jugador encontrado: " + jugador);
                } else {
                    System.out.println("Jugador con alias \"" + aliasConsulta + "\" no encontrado.");
                }

            } else if (opcion == 3) {
                System.out.print("Introduce el alias del jugador cuyo correo deseas editar: ");
                String aliasCorreo = scanner.nextLine();
                System.out.print("Introduce el nuevo correo electrónico: ");
                String nuevoCorreo = scanner.nextLine();
                gestionJugador.editarCorreo(aliasCorreo, nuevoCorreo);

            } else if (opcion == 4) {
                System.out.print("Introduce el alias del jugador cuyo alias deseas editar: ");
                String aliasEdicion = scanner.nextLine();
                System.out.print("Introduce el nuevo alias: ");
                String nuevoAlias = scanner.nextLine();
                gestionJugador.editarAlias(aliasEdicion, nuevoAlias);

            } else if (opcion == 5) {
                System.out.print("Introduce el alias del jugador: ");
                String aliasPartida = scanner.nextLine();
                System.out.print("Introduce el puntaje de la partida: ");
                int puntos = scanner.nextInt();
                scanner.nextLine();
                System.out.print("¿El jugador ganó la partida? (true/false): ");
                boolean gano = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Introduce el tiempo total de la partida (en segundos): ");
                long tiempoTotal = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Introduce el número de palabras colocadas: ");
                int palabrasColocadas = scanner.nextInt();
                scanner.nextLine();
                Partida partida = new Partida(aliasPartida, puntos, gano, tiempoTotal, palabrasColocadas);
                gestionJugador.agregarPartida(partida);

            } else if (opcion == 6) {
                System.out.print("Introduce el alias del jugador para mostrar sus estadísticas: ");
                String aliasEstadisticas = scanner.nextLine();
                gestionJugador.mostrarEstadisticasDePartidas(aliasEstadisticas);

            } else if (opcion == 7) {
                System.out.println("Volviendo a menú principal...");
                break;

            } else {
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
    }
}

