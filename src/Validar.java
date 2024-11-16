import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

    private boolean emailCorrecto(String email){
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void emailValido(String email) throws FormatoInvalido {
        if(!emailCorrecto(email)) throw new FormatoInvalido();
    }

    public String validarEmail(Scanner scanner, Validar validar){
        boolean correoValido = false;
                String correo = null;
                while (!correoValido) {
                    System.out.print("Introduce el correo del jugador: ");
                    correo = scanner.nextLine();
                    try {
                        validar.emailValido(correo);
                        correoValido = true;
                    } catch (FormatoInvalido e) {
                        System.out.println("Error al ingresar correo. Escriba uno valido.");
                    }
                }
                return correo;
    }

}