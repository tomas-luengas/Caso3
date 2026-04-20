package caso3;

public class Clasificador extends Thread {

    private Buzon buzonClasificacion;
    private Buzon[] buzonesServidor;
    private int nClasificadores;
    private int nServidores;
    private static int clasificadoresActivos = 0;

    public Clasificador(Buzon buzonClasificacion, Buzon[] buzonesServidor, int nClasificadores, int nServidores) {
        this.buzonClasificacion = buzonClasificacion;
        this.buzonesServidor = buzonesServidor;
        this.nClasificadores = nClasificadores;
        this.nServidores = nServidores;
        this.clasificadoresActivos = nClasificadores;
    }


    @Override
    public void run() {
        try {
            while(true) {
                Evento evento = buzonClasificacion.retirar();
                if (evento.isEsFin()) {
                    clasificadoresActivos--;
                    if(clasificadoresActivos == 0) {
                        for (int i = 0; i < nServidores; i++) {
                            buzonesServidor[i].agregar(new Evento("FIN", 0, true));
                        }
                    }               
                    System.out.println("Clasificador recibió señal de fin.");
                    break;
                } else {
                    buzonesServidor[evento.getTipo() - 1].agregar(evento);
                    System.out.println("Evento ya hace parte del buzon de servidores.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}