public class Impresora implements Runnable {

    // Atributos que identifican la impresora y el número de páginas.
    private String nombre;
    private int numeroPaginas;

    // Constructor que recibe el nombre y las páginas que debe imprimir.
    public Impresora(String nombre, int numeroPaginas) {
        this.nombre = nombre;
        this.numeroPaginas = numeroPaginas;
    }

    // Metodo que ejecuta la tarea de impresión en un hilo independiente.
    @Override
    public void run() {

        // Recorremos todas las páginas que debe imprimir la impresora.
        for (int pagina = 1; pagina <= numeroPaginas; pagina++) {

            System.out.println(nombre + " está imprimiendo la página " + pagina + " de " + numeroPaginas);

            try {
                // Simulamos el tiempo necesario para imprimir cada página.
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                // Restauramos el estado de interrupción del hilo.
                Thread.currentThread().interrupt();
                System.out.println(nombre + " ha interrumpido su impresión.");

                // Finalizamos la tarea si el hilo ha sido interrumpido.
                return;
            }
        }

        // Mensaje que indica que la impresora ha terminado.
        System.out.println(nombre + " ha terminado de imprimir.");
    }
}