public class Utilizzatore {

    public static void main(String[] args) {
 
        int n = 10;
        Lista l = new ListaVuota();
        for (int i = 0; i < n; i++)
            l = l.aggiungi(i);

        Visitatore v1 = new Stampa();
        Visitatore v2 = new Somma();
        l.accetta(v1);
        l.accetta(v2);
        System.out.println("Somma: " + ((Somma)v2).getSomma());

    }
}
