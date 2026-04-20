package caso3;

public class Servidor extends Thread {

    private Buzon buzonConsolidacion;

    public Servidor(Buzon buzonConsolidacion) {
        this.buzonConsolidacion = buzonConsolidacion;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Evento evento = buzonConsolidacion.retirar();
                if (evento.isEsFin()) {
                    System.out.println("Servidor recibió señal de fin.");
                    break;
                } else {
                    long tiempo = (long)(Math.random() * 901) + 100;
                    Thread.sleep(tiempo);
                    System.out.println("Servidor procesó evento: " + evento.getId());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}