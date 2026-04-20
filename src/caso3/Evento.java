package caso3;

public class Evento {
    private String id;
    private int tipo;
    private boolean esFin;

    public Evento(String id, int tipo, boolean esFin) {
        this.id = id;
        this.tipo = tipo;
        this.esFin = esFin;
    }   

    public String getId() {
        return id;
    }   

    public int getTipo() {
        return tipo;
    }

    //Le dice a clasificador "apagarte en caso de que no haya 
    //a entrar otro evento o "si", el evento que viene es real".
    public boolean isEsFin() {
        return esFin;
    }

}