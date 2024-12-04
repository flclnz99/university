import java.util.Comparator;
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
  public static void main(String[] args) throws PriorityQueueException{
    PriorityQueue<Integer> intqueue = new PriorityQueue<>(new IntegerComparator());
    PriorityQueue<String> stringqueue = new PriorityQueue<>(new StringComparator());
    //System.out.println(queue.empty());
    //System.out.println(queue.getSize());
    stringqueue.print();
    stringqueue.push("ciao");
    System.out.println(stringqueue.size());
    stringqueue.pop();
    stringqueue.push("mi");
    stringqueue.push("chiamo");
    stringqueue.push("luca");
    stringqueue.push("no");
    stringqueue.print();
    //System.out.println(stringqueue.contains("chiamo"));
    //System.out.println("\nestraggo il minimo\n");
    //System.out.println(stringqueue.contains("chiamo"));
    //System.out.println(queue.getSize());
    //stringqueue.print();
  }
  
}
