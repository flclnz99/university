public class Stampa implements Visitatore {

    public void visita(Nodo nodo) {
        System.out.print(nodo.getElem() + " ");
    }
    public void visita(ListaVuota fl) {
        System.out.println("");
    }

}
