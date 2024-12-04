public class TestSet {
  public static void main(String[] args) {
    System.out.println("prima lista:");
    Set<Integer> s = new Set<>();
    s.add(1);
    s.add(0);
    s.print();
    //System.out.println(s.contains(1));  //true
    //System.out.println(s.contains(19));  //false
    //System.out.println(s.remove(-31));  //false
    System.out.println("seconda lista:");
    Set<Integer> d = new Set<>();
    d.add(4);
    d.add(4);
    d.add(1);
    //s.add(-1);
    d.print();
    System.out.println("       ");
    //System.out.println(d.subsetOf(s));
    Set<Integer> cross=s.intersection(d);
    cross.print();
  }
}
