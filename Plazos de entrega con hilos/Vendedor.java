import java.util.Random;

public class Vendedor implements Runnable {

    // Nombre del vendedor.
    private String nombre;

    // Días que tardará en entregar el producto.
    private int diasEntrega;

    // Constructor que recibe el nombre del vendedor.
    public Vendedor(String nombre) {
        this.nombre = nombre;
    }

    // Metodo que ejecuta la consulta del plazo de entrega.
    @Override
    public void run() {

        try {
            // Simulamos el tiempo que tarda el vendedor en consultar.
            Thread.sleep(500);

            // Generamos un número aleatorio entre 1 y 7.
            Random random = new Random();
            diasEntrega = random.nextInt(7) + 1;
            System.out.println(nombre + " puede entregar el producto en " + diasEntrega + " días.");

        } catch (InterruptedException e) {

            // Restauramos el estado de interrupción del hilo.
            Thread.currentThread().interrupt();
            System.out.println(nombre + " ha interrumpido su consulta.");
        }
    }

    // Devuelve el nombre del vendedor.
    public String getNombre() {
        return nombre;
    }

    // Devuelve los días de entrega.
    public int getDiasEntrega() {
        return diasEntrega;
    }
}