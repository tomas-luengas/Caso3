package caso3;

import java.util.LinkedList;
import java.util.Queue;

public class Buzon {

    private Queue<Evento> cola;
    private int capacidad;

//-1 lo vamos a usar para marcar que es ilimitado. 
    public Buzon(int capacidad) {
        if(capacidad < -1) { 
            throw new IllegalArgumentException("La capacidad debe ser un número positivo.");
        }
        this.cola = new LinkedList<>();
        this.capacidad = capacidad; 
    }

    public synchronized void agregar(Evento e) throws InterruptedException {
        while (capacidad != -1 && cola.size() >= capacidad) {
            wait();
        }
        cola.add(e);
        notifyAll();
    }

    public synchronized Evento retirar() throws InterruptedException {
        while (cola.isEmpty()) {
            wait();
        }
        Evento e = cola.poll();
        notifyAll();
        return e;
    }
}