public class RubricaDemo {
    public static void main(String[] args) {
        Rubrica R = new Rubrica(100);
        //System.out.println("(1) Rubrica con contatti a,b,c: ");
        R.aggiungi("c","c@ditta");
        R.aggiungi("b","b@ditta");
        R.aggiungi("a","a@ditta");
        
        R.scriviOutput();
        R.sort();
        R.scriviOutput();
/* 
        System.out.println("(2) Rimuovo a");
        R.rimuovi("a");
        R.scriviOutput();

        System.out.println("(3) Aggiungo b (ma c'e' gia'): successo = " + 
                           R.aggiungi("b","e"));
        R.scriviOutput();

        System.out.println("(4) Modifico b in b2: successo = " + 
                           R.cambiaNome("b","b2"));
        R.scriviOutput();

        System.out.println("(5) Modifico b@ditta in b2@ditta: successo = " + 
                           R.cambiaEmail("b2","b2@ditta"));
        R.scriviOutput();
*/

    }  
}