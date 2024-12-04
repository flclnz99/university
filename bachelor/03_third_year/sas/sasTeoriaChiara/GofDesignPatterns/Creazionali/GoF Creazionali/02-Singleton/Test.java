public class Test {

    public static void main(String args[]) {
    
         //PrinterSpooler_a printer = new PrinterSpooler_a();

         PrinterSpooler_a.print("Stampa questo");

         PrinterSpooler_b theUnique = PrinterSpooler_b.getInstance();

         theUnique.print("Stampa quest'altro");

         //if we try this:
         PrinterSpooler_b mayBeOther = PrinterSpooler_b.getInstance();
         
         mayBeOther.print("Stampa ancora quest'altro");
         
         //then...
         if ( theUnique != mayBeOther ) System.out.println("Diverso!");

    }

}
