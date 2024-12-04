//RubricaDemo.java
public class RubricaDemo {
    public static void main(String[] args) {
        Rubrica R = new Rubrica(5);

        // Aggiungo alcuni elementi iniziali
        System.out.println("(1) Rubrica con contatti a,b,c,d,e: ");
        R.aggiungi("orange", "b@ditta");
        R.aggiungi("apple", "a@ditta");
        R.aggiungi("c", "c@ditta");
        R.aggiungi("d", "d@ditta");
        R.aggiungi("e", "e@ditta");
        R.aggiungi("f", "f@ditta");
        R.scriviOutput();
        //R.ordinaRubrica();
        //R.scriviOutput();
        //System.out.println("e-mail di c=" + R.cercaEmail("c"));

        //System.out.println("(2) Rimuovo a");
        //R.rimuovi("a");
        //R.scriviOutput();

        //System.out.println("(3) Aggiungo b (ma c'e' gia'): successo = "
        //                   + R.aggiungi("b", "e"));
        //R.scriviOutput();

        //System.out.println("(4) Modifico b in b2: successo = "
        //                 + R.cambiaNome("b", "b2"));
        //R.scriviOutput();

        //System.out.println("(5) Modifico b@ditta in b2@ditta: successo = "
        //                   + R.cambiaEmail("b2", "b2@ditta"));
        //R.scriviOutput();
    }
}
