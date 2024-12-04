public class TestDynamicQueue {

    public static void main(String[] args) {
        DynamicQueue<Integer> qInt = new DynamicQueue<>();
        System.out.println("qInt = {17,42,4} ");
        qInt.enqueue(17); 
        qInt.enqueue(42); 
        qInt.enqueue(4);
        qInt.scriviOutput();
        System.out.println("qInt.empty() = " + qInt.empty());
        System.out.println("qInt.contains(4)  = " + qInt.contains(4)); //true
        System.out.println("qInt.contains(40) = " + qInt.contains(40));//false
        System.out.println("qInt.size() = " + qInt.size());  // stampa 3
        System.out.println("qInt.front()= " + qInt.front()); // stampa 17  
        System.out.println(qInt.dequeue()); //toglie e stampa 17
        System.out.println(qInt.dequeue()); //toglie e stampa 42
        System.out.println(qInt.dequeue()); //toglie e stampa 4: coda vuota
        // gli elementi vengono stampati nello stesso ordine in cui
        // sono stati inseriti, dal momento che la coda e' una
        // struttura FIFO (First-In-First-Out)
        System.out.println("qInt.empty() = " + qInt.empty());

        DynamicQueue<Double> qDouble = new DynamicQueue<>();
        System.out.println("\nqDouble = {0.5,3.14,4.0,1.2} ");
        qDouble.enqueue(0.5); 
        qDouble.enqueue(3.14); 
        qDouble.enqueue(4.0);
        qDouble.enqueue(1.2);
        qDouble.scriviOutput();
        System.out.println("qDouble.empty() = " + qDouble.empty());
        System.out.println("qDouble.contains(3.14)  = " + qDouble.contains(3.14)); //true
        System.out.println("qDouble.contains(40.0) = " + qDouble.contains(40.0));//false
        System.out.println("qDouble.size() = " + qDouble.size());  // stampa 4
        System.out.println("qDouble.front()= " + qDouble.front()); // stampa 0.5  
        System.out.println(qDouble.dequeue()); //toglie e stampa 0.5
        System.out.println(qDouble.dequeue()); //toglie e stampa 3.14
        System.out.println(qDouble.dequeue()); //toglie e stampa 4.0
        System.out.println("qDouble.empty() = " + qDouble.empty());

        DynamicQueue<String> qString = new DynamicQueue<>();
        System.out.println("\nqString = {qui,quo,qua} ");
        qString.enqueue("qui"); 
        qString.enqueue("quo"); 
        qString.enqueue("qua");
        qString.scriviOutput();
        System.out.println("qString.empty() = " + qString.empty());
        System.out.println("qString.contains(\"qui\")  = " + qString.contains("qui")); //true
        System.out.println("qString.contains(\"paperino\") = " + qString.contains("paperino"));//false
        System.out.println("qString.size() = " + qString.size());  // stampa 3
        System.out.println("qString.front()= " + qString.front()); // stampa "qui"  
        System.out.println(qString.dequeue()); //toglie e stampa "qui"
        System.out.println(qString.dequeue()); //toglie e stampa "quo"
        System.out.println(qString.dequeue()); //toglie e stampa "qua"
        System.out.println("qString.empty() = " + qString.empty());

        DynamicQueue<Person> qPerson = new DynamicQueue<>();
        System.out.println("\nqPerson = {<Mario,Rossi>,<Luisa,Verdi>,<Carla,Bianchi>} ");
        Person p1 = new Person("Mario","Rossi");
        Person p2 = new Person("Luisa","Verdi");
        Person p3 = new Person("Carla","Bianchi");
        qPerson.enqueue(p1); 
        qPerson.enqueue(p2); 
        qPerson.enqueue(p3);
        qPerson.scriviOutput();
        System.out.println("qPerson.empty() = " + qPerson.empty());
        System.out.println("qPerson.contains(p1)  = " + qPerson.contains(p1)); //true
        System.out.println("qPerson.contains(new Person(\"Mario\",\"Rossi\")) = " + 
        qPerson.contains(new Person("Mario","Rossi")));//false...si noti che
        //il metodo contains(...) in DynamicQueue è basato sull'implementazione
        //di default del metodo equals(...) di Java, il quale considera uguali due
        //oggetti se e solo se sono lo stesso oggetto. Questo criterio di uguaglianza
        //è adatto in questo contesto in cui si confrontano oggetti di tipo
        //Person, in quanto due persone p1 e p2 sono uguali se e solo se
        //sono la stessa persona (e non, ovviamente, se sono due persone diverse,
        //anche se omonime)
        System.out.println("qPerson.size() = " + qPerson.size());  // stampa 3
        System.out.println("qPerson.front()= " + qPerson.front()); // stampa <Mario,Rossi>  
        System.out.println(qPerson.dequeue()); //toglie e stampa <Mario,Rossi>
        System.out.println(qPerson.dequeue()); //toglie e stampa <Luisa,Verdi>
        System.out.println(qPerson.dequeue()); //toglie e stampa <Carla,Bianchi>
        System.out.println("qPerson.empty() = " + qPerson.empty());
    }
}

