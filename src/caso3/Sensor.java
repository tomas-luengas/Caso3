package caso3;

public class Sensor extends Thread {

    private Buzon buzonEntrada;
    private int id;
    private int numeroEventos;
    private int nServidores;

   public Sensor(int id, int numeroEventos, int nServidores, Buzon buzon) {
        this.id = id;
        this.numeroEventos = numeroEventos;
        this.buzonEntrada = buzon;
        this.nServidores = nServidores;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numeroEventos; i++) {
                int tipoEvento = (int)(Math.random() * nServidores) + 1;
                Evento evento = new Evento(id + "-evento" + i, tipoEvento, false);
                buzonEntrada.agregar(evento);
                System.out.println("Sensor " + id + " generó evento: " + evento.getId());
            }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }