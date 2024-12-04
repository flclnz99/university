package priorityqueue;

import java.util.Comparator;
import graph.*;
import java.lang.Integer;

public class App {
    static class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(Integer.valueOf(o1),Integer.valueOf(o2));
        }
    }
    static class StringComparator implements Comparator<String>{
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
    static class EdgeComparator implements Comparator<Edge<String,Double>>{
        @Override
        public int compare(Edge<String,Double> o1, Edge<String,Double> o2) {
            double x = o1.getLabel();
		    double y = o2.getLabel();
		    return x < y ? -1 : x == y ? 0 : 1;
        }
    }
    public static void main(String[] args) throws PriorityQueueException{
        PriorityQueue<Integer> intqueue = new PriorityQueue<>(new IntegerComparator());
        PriorityQueue<String> stringqueue = new PriorityQueue<>(new StringComparator());
        PriorityQueue<Edge<String,Double>> edgequeue = new PriorityQueue<>(new EdgeComparator());
        Edge<String,Double> uno = new Edge<>("a","e",1.0);
        Edge<String,Double> due = new Edge<>("a","b",5.0);
        //uno.printEdge();
        //due.printEdge();
        //System.out.println("\n");
        //edgequeue.push(uno);
        //edgequeue.push(due);
        //edgequeue.pop().printEdge();
        //edgequeue.pop().printEdge();
        
        //System.out.println(queue.empty());
        //System.out.println(queue.getSize());
        
        System.out.println("inserisco 3,5,1,2");
        intqueue.push(3);
        intqueue.push(5);
        intqueue.push(1);
        intqueue.push(2);
        System.out.println("stampo la lista ordinata:");
        intqueue.print();
        System.out.print("list size: "+intqueue.size());
        System.out.println("\n");

        System.out.println("rimuovo l'elemento 2");
        intqueue.remove(2);
        System.out.println("stampo la lista ordinata:");
        intqueue.print();
        System.out.print("list size: "+intqueue.size());
        System.out.println("\n");

        System.out.println("Tolgo il minimo (1)");
        System.out.println("stampo la lista");
        int elem = intqueue.pop();
        intqueue.print();
        System.out.println("elemento rimosso: "+elem);
        System.out.println("list size: "+intqueue.size());
        System.out.println("\n");

        System.out.println("inserisco 10");
        System.out.println("stampo la lista");
        intqueue.push(10);
        intqueue.print();
        System.out.println("list size: "+intqueue.size());
        System.out.println("\n");

        System.out.println("Tolgo il minimo (2)");
        System.out.println("stampo la lista");
        int elem2 = intqueue.pop();
        intqueue.print();
        System.out.println("elemento rimosso: "+elem2);
        System.out.println("list size: "+intqueue.size());
        System.out.println("\n");
        
        //System.out.println(stringqueue.contains("chiamo"));
        //System.out.println("\nestraggo il minimo\n");
        //System.out.println(stringqueue.contains("chiamo"));
        //System.out.println(queue.getSize());
        //stringqueue.print();
        
    }

}
