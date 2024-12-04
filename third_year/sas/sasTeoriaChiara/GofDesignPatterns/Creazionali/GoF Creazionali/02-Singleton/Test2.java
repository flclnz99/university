public class Test2 {

    public static void main(String args[]) {
    
         //PrinterSpooler_a printer = new PrinterSpooler_a();

         PrinterSpooler_a.print("Stampa questo");

         PrinterSpooler_b_golosa theUnique = PrinterSpooler_b_golosa.getInstance();

         theUnique.print("Stampa quest'altro");

         //if we try this:
         PrinterSpooler_b_golosa mayBeOther = PrinterSpooler_b_golosa.getInstance();

         mayBeOther.print("Stampa ancora quest'altro");
         
         //then...
         if ( theUnique != mayBeOther ) System.out.println("Diverso!");

    }

}
