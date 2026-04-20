package caso3;

public class Clasificador extends Thread {

    private Buzon buzonClasificacion;
    private Buzon[] buzonesServidor;
    private int nClasificadores;

    public Clasificador(Buzon buzonClasificacion, Buzon[] buzonesServidor, int nClasificadores) {
        this.buzonClasificacion = buzonClasificacion;
        this.buzonesServidor = buzonesServidor;
        this.nClasificadores = nClasificadores;
    }


    @Override
    public void run() {
        try {
            
        } catch (Exception e) {
        }
    }




}