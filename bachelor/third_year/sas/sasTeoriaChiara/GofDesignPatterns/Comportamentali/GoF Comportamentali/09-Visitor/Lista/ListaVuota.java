public class ListaVuota extends Lista {

    public ListaVuota() { } 

    public boolean empty() { 
        return true; 
    }

    public int getElem() {
        assert false; return 0;
    }

    public Lista aggiungi(int x) {
        return new Nodo(x, this);
    } 

    public Lista rimuoviTesta() {
        assert false; return null;
    }

    public void accetta(Visitatore visitatore) { 
        visitatore.visita(this);
    }

}
