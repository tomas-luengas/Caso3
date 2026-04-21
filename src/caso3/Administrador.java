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
                        Evento eventoFin = new Evento("FIN", 0, true);
                        while (!buzonClasificacion.intentarAgregar(eventoFin)){
                            Thread.yield();
                        }
                    }
                break;
                
                } else {
                    int numero = (int)(Math.random() * 21);
                    // es anómalo → buzonAlertas
                    if (numero % 4 != 0) {
                        System.out.println("Administrador: " + evento.getId() + " era malicioso. Fue descartado: " + evento.getId());
                    // es normal → buzonClasificacion
                    } else {
                    while(!buzonClasificacion.intentarAgregar(evento)){
                        Thread.yield();
                    }
                    System.out.println("Administrador: " + evento.getId() + " era inofensivo. Enviado a buzonClasificacion.");
                }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        }
}