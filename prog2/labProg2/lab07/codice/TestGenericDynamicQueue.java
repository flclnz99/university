public class TestGenericDynamicQueue{
  public static void main(String[] args) {
      System.out.println("/**INIZIO TEST CODA GENERICA CON INTERI**/");
      GenericDynamicQueue<Integer> i = new GenericDynamicQueue<>();
      System.out.println( "q = {17,42,4} " );
      i.enqueue(17);
      i.enqueue(42);
      i.enqueue(4); 
      i.scriviOutput();

      System.out.println( "i.empty() = " + i.empty());
      System.out.println( "i.contains(4)  = " + i.contains(4));
      System.out.println( "i.contains(40) = " + i.contains(40));
      System.out.println("i.size() = " + i.size());
      System.out.println("i.front()= " + i.front());
      System.out.println(i.dequeue());
      System.out.println(i.dequeue());
      System.out.println(i.dequeue());
      System.out.println( "i.empty() = " + i.empty());
      System.out.println("/**FINE TEST CODA GENERICA CON INTERI**/");

      System.out.println("   ");

      System.out.println("/**INIZIO TEST CODA GENERICA CON DOUBLE**/");
      GenericDynamicQueue<Double> d = new GenericDynamicQueue<>();
      System.out.println( "d = {17.25,42.5,4.78} " );
      d.enqueue(17.25);
      d.enqueue(42.5);
      d.enqueue(4.78); 
      d.scriviOutput();

      System.out.println( "d.empty() = " + d.empty());
      System.out.println( "d.contains(4)  = " + d.contains(4.78)); //true
      System.out.println( "d.contains(40) = " + d.contains(40.8));//false
      System.out.println("d.size() = " + d.size());  // stampa 3
      System.out.println("d.front()= " + d.front()); // stampa 17.25  
      System.out.println(d.dequeue()); //toglie e stampa 17.25
      System.out.println(d.dequeue()); //toglie e stampa 42.5
      System.out.println(d.dequeue()); //toglie e stampa 4.78: coda vuota

      // gli elementi vengono stampati nello stesso ordine in cui
      // sono stati inseriti, dal momento che la coda e' una
      // struttura FIFO (First-In-First-Out)
      System.out.println( "d.empty() = " + d.empty());
      System.out.println("/**FINE TEST CODA GENERICA CON DOUBLE**/");

      System.out.println("   ");

      System.out.println("/**INIZIO TEST CODA GENERICA CON PERSON**/");
      GenericDynamicQueue<Person> p = new GenericDynamicQueue<>();
      System.out.println( "d = {<Lorenzo,Falchi>,<MA,Farru>,<Mirella,Foddai>} " );
      Person lorenzo = new Person("Lorenzo", "Falchi");
      Person ma = new Person("MA", "Farru");
      Person mirella = new Person("Mirella", "Foddai");
      Person marco = new Person("Marco", "Aurelio");
      p.enqueue(lorenzo);
      p.enqueue(ma);
      p.enqueue(mirella); 
      p.scriviOutput();

      System.out.println( "p.empty() = " + p.empty());
      System.out.println( "p.contains(lorenzo)  = " + p.contains(lorenzo)); //true
      System.out.println( "p.contains(marco) = " + p.contains(marco));//false
      System.out.println("p.size() = " + p.size());  // stampa 3
      System.out.println("p.front()= " + p.front()); // stampa Lorenzo Falchi  
      System.out.println(p.dequeue()); //toglie e stampa Lorenzo Falchi  
      System.out.println(p.dequeue()); //toglie e stampa MA Farru
      System.out.println(p.dequeue()); //toglie e stampa Mirella Foddai: coda vuota

      // gli elementi vengono stampati nello stesso ordine in cui
      // sono stati inseriti, dal momento che la coda e' una
      // struttura FIFO (First-In-First-Out)
      System.out.println( "p.empty() = " + p.empty());
      System.out.println("/**FINE TEST CODA GENERICA CON PERSON**/");
  }

}

