package caso3;

public class Administrador extends Thread {

    private Buzon buzonAlertas;
    private Buzon buzonClasificacion;
    private int nClasificadores;

    public Administrador(Buzon buzonAlertas, Buzon buzonClasificacion, int nClasificadores) {
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        this.nClasificadores = nClasificadores;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Evento evento = buzonAlertas.retirar();
                if (evento.isEsFin()) {
                    System.out.println("Administrador recibió señal de fin.");
                    for (int i = 0; i < nClasificadores; i++) {
                      buzonClasificacion.agregar(new Evento("FIN", 0, true));
                    }
                break;
                
                } else {
                    int numero = (int)(Math.random() * 21);
                    // es anómalo → buzonAlertas
                    if (numero % 4 == 0) {
                        System.out.println("Broker " + evento.getId() + " envió alerta: " + evento.getId());
                    // es normal → buzonClasificacion
                    } else {
                    buzonClasificacion.agregar(evento);
                    System.out.println("Broker " + evento.getId() + " enviado a buzonClasificacion");
                }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        }
}