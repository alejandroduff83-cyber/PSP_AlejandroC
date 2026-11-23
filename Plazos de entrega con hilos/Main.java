public class Main {

    public static void main(String[] args) {

        // Creamos la tarea del cliente.
        Cliente cliente = new Cliente();

        // Asociamos la tarea a un hilo.
        Thread hiloCliente = new Thread(cliente);

        // Mostramos el estado del hilo antes de iniciarlo.
        System.out.println("Estado inicial del cliente: " + hiloCliente.getState());

        // Iniciamos el hilo del cliente.
        hiloCliente.start();

        try {
            // Esperamos a que el cliente termine su trabajo.
            hiloCliente.join();

        } catch (InterruptedException e) {

            // Restauramos el estado de interrupción del hilo principal.
            Thread.currentThread().interrupt();
            System.out.println("El hilo principal ha sido interrumpido.");
        }

        // Mostramos el estado del cliente después de finalizar.
        System.out.println("\nEstado final del cliente: " + hiloCliente.getState());
    }
}