package caso3;


public class Broker extends Thread {

    private Buzon buzonEntrada;
    private String id;
    private Buzon buzonAlertas;
    private Buzon buzonClasificacion;
    private int totalEventos;

    public Broker(String id, Buzon buzonEntrada, Buzon buzonAlertas, Buzon buzonClasificacion, int totalEventos) {
        this.id = id;
        this.buzonEntrada = buzonEntrada;
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        this.totalEventos = totalEventos;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < totalEventos; i++) {
                Evento evento = buzonEntrada.retirar();
                System.out.println("Broker " + id + " procesó evento: " + evento.getId());
                int numero = (int)(Math.random() * 201);
                // es anómalo → buzonAlertas
                if (numero % 8 == 0) {
                    buzonAlertas.agregar(evento);
                    System.out.println("Broker " + id + " envió alerta: " + evento.getId());
                // es normal → buzonClasificacion
                    } else {
                    while (!buzonClasificacion.intentarAgregar(evento)) {
                        Thread.yield();
                    }
                    System.out.println("Broker " + id + " envió a clasificar: " + evento.getId());
                }
            }
            buzonAlertas.agregar(new Evento("FIN", 0, true));
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}