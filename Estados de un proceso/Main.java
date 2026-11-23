import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        // Comprobamos el sistema operativo para utilizar el comando adecuado.
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();

        ProcessBuilder processBuilder;

        if (sistemaOperativo.contains("win")) {
            processBuilder = new ProcessBuilder("ping", "-n", "4", "localhost");
        } else {
            processBuilder = new ProcessBuilder("ping", "-c", "4", "localhost");
        }

        // Unificamos la salida estándar y la salida de errores.
        processBuilder.redirectErrorStream(true);

        try {
            // Iniciamos el proceso ping.
            Process proceso = processBuilder.start();

            // Mostramos información inicial del proceso.
            System.out.println("Información del proceso iniciado:");
            System.out.println("¿El proceso está vivo?: " + proceso.isAlive());

            // Obtenemos la información del proceso.
            ProcessHandle.Info informacion = proceso.info();

            System.out.println("PID: " + proceso.pid());
            System.out.println("Comando: " + informacion.command().orElse("No disponible"));
            System.out.println("Argumentos: " + informacion.arguments().map(argumentos -> String.join(" ", argumentos)).orElse("No disponibles"));
            System.out.println("Usuario: " + informacion.user().orElse("No disponible"));

            // Leemos y mostramos el resultado del comando ping.
            System.out.println("Resultado del comando ping:");

            try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;

                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // Esperamos a que el proceso termine.
            int resultado = proceso.waitFor();

            System.out.println("Resultado final");
            System.out.println("Código de finalización: " + resultado);

            if (resultado == 0) {
                System.out.println("El proceso ha finalizado correctamente.");
            } else {
                System.out.println("El proceso ha finalizado con errores.");
            }
            // Comprobamos si el proceso sigue vivo después de finalizar.
            System.out.println("¿El proceso sigue vivo?: " + proceso.isAlive());

        } catch (IOException e) {
            // Controlamos errores al iniciar o leer el proceso.
            System.out.println("Error al ejecutar el proceso: " + e.getMessage());

        } catch (InterruptedException e) {
            // Restauramos el estado de interrupción del hilo.
            Thread.currentThread().interrupt();

            System.out.println("La ejecución del proceso ha sido interrumpida.");

        } catch (SecurityException e) {

            // Controlamos posibles restricciones de seguridad.
            System.out.println("No hay permisos para ejecutar el proceso: "
                    + e.getMessage());
        }
    }
}