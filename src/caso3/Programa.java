package caso3;

import java.io.BufferedReader;
import java.io.FileReader;

public class Programa {
    public static void main(String[] args) {
    try {
        BufferedReader br = new BufferedReader(new FileReader("Archivo.txt"));
        int nSensores = Integer.parseInt(br.readLine().split("=")[1].trim());
        int base = Integer.parseInt(br.readLine().split("=")[1].trim());
        int nClasificadores = Integer.parseInt(br.readLine().split("=")[1].trim());
        int nServidores = Integer.parseInt(br.readLine().split("=")[1].trim());
        int tam1 = Integer.parseInt(br.readLine().split("=")[1].trim());
        int tam2 = Integer.parseInt(br.readLine().split("=")[1].trim());

        Buzon buzonEntrada = new Buzon(-1);
        Buzon buzonAlertas = new Buzon(-1);
        Buzon buzonClasificacion = new Buzon(tam1);
        Buzon[] buzonesServidor = new Buzon[nServidores];
        for (int i = 0; i < nServidores; i++) {
            buzonesServidor[i] = new Buzon(tam2);
        }

        Servidor[] servidores = new Servidor[nServidores];
        for(int i = 0; i < nServidores; i++) {
            servidores[i] = new Servidor(buzonesServidor[i]);
            servidores[i].start();
        }

        Clasificador[] clasificadores = new Clasificador[nClasificadores];
        for(int i = 0; i < nClasificadores; i++) {
            clasificadores[i] = new Clasificador(buzonClasificacion, buzonesServidor, nClasificadores, nServidores);
            clasificadores[i].start();
        }

        Administrador administrador = new Administrador(buzonAlertas, buzonClasificacion, nClasificadores);
        administrador.start();

        int totalEventos = 0;
        for (int i = 1; i <= nSensores; i++) {
            totalEventos += base * i;
        }
        Broker broker = new Broker("Broker1", buzonEntrada, buzonAlertas, buzonClasificacion, totalEventos);
        broker.start();

        Sensor[] sensores = new Sensor[nSensores];
        for (int i = 0; i < nSensores; i++) {
            sensores[i] = new Sensor(i + 1, base * (i + 1), nServidores, buzonEntrada);
            sensores[i].start();
        }

        br.close();
    } catch (Exception e) {
        System.out.println("Error leyendo Archivo.txt");
    }
    }
}