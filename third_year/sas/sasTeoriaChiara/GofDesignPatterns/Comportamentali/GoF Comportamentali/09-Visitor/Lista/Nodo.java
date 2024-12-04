public class Nodo extends Lista {

    private int elem; 
    private Lista next; 

    public Nodo(int elem, Lista next) {
        this.elem = elem;
        this.next = next;
    }

    public boolean empty() { 
        return false; 
    }

    public int getElem() { 
        return elem;
    }

    public Lista aggiungi(int x) {
        return new Nodo(x, this);
    }

    public Lista rimuoviTesta() {
        return next;
    }

    public void accetta(Visitatore visitatore) {
        visitatore.visita(this);
        next.accetta(visitatore);
    }

}
