public class PrinterSpooler_c {

   private static PrinterSpooler_c instance;

   private PrinterSpooler_c() { }

   public static synchronized PrinterSpooler_c getInstance() {
      if (instance==null) {
         instance = new PrinterSpooler_c();
      }
      return instance;
   }

   public void print (String msg) {
      System.out.println(msg);
   }

}
