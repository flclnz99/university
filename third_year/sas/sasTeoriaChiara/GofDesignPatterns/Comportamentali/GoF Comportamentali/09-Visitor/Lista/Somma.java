public class Somma implements Visitatore {

    private int somma;

    public Somma() {
        this. somma = 0;
    }

    public int getSomma() {
        return somma;
    }

    public void visita(Nodo nodo) {
        somma += nodo.getElem();
    }
    public void visita(ListaVuota fl) {  }

}
