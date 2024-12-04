public class PrinterSpooler_b_golosa {

   static {
      instance = new PrinterSpooler_b_golosa();
   }

   private static PrinterSpooler_b_golosa instance;

   private PrinterSpooler_b_golosa() { }

   public static PrinterSpooler_b_golosa getInstance() {
      return instance;
   }

   public void print (String msg) {
      System.out.println(msg);
   }

}
