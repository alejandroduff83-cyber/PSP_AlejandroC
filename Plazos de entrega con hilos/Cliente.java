public class Cliente implements Runnable {

    @Override
    public void run() {

        // Creamos un grupo para agrupar los tres hilos vendedores.
        ThreadGroup grupoVendedores = new ThreadGroup("GrupoVendedores");

        // Creamos los tres vendedores.
        Vendedor vendedor1 = new Vendedor("Vendedor 1");
        Vendedor vendedor2 = new Vendedor("Vendedor 2");
        Vendedor vendedor3 = new Vendedor("Vendedor 3");

        // Creamos un hilo independiente para cada vendedor.
        Thread hilo1 = new Thread(grupoVendedores, vendedor1);
        Thread hilo2 = new Thread(grupoVendedores, vendedor2);
        Thread hilo3 = new Thread(grupoVendedores, vendedor3);

        // Iniciamos todos los vendedores antes de esperar sus respuestas.
        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            // Esperamos a que terminen los tres vendedores.
            hilo1.join();
            hilo2.join();
            hilo3.join();

        } catch (InterruptedException e) {

            // Restauramos el estado de interrupción del cliente.
            Thread.currentThread().interrupt();
            System.out.println("El cliente ha interrumpido la espera.");
            return;
        }

        // Guardamos los vendedores en una lista para compararlos.
        Vendedor[] vendedores = {vendedor1, vendedor2, vendedor3};

        // Inicialmente seleccionamos el primer vendedor.
        Vendedor mejorVendedor = vendedores[0];

        // Recorremos los vendedores para encontrar el menor plazo.
        for (int i = 1; i < vendedores.length; i++) {

            if (vendedores[i].getDiasEntrega() < mejorVendedor.getDiasEntrega()) {
                mejorVendedor = vendedores[i];
            }
        }

        // Mostramos el resultado de la comparación.
        System.out.println("Mejor oferta");
        System.out.println("Vendedor elegido: " + mejorVendedor.getNombre());
        System.out.println("Plazo de entrega: " + mejorVendedor.getDiasEntrega() + " días.");
    }
}