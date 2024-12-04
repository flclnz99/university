public class PrinterSpooler_b {

   private static PrinterSpooler_b instance = null;

   private PrinterSpooler_b() { }

   public static PrinterSpooler_b getInstance() {
      if (instance==null) {
         instance = new PrinterSpooler_b();
      }
      return instance;
   }

   public void print (String msg) {
      System.out.println(msg);
   }

}
