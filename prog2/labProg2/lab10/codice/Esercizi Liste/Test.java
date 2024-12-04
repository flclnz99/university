public class Test {
	public static void main(String[] args) {
        List l1 = new Nil();
        System.out.println("prima: " + l1);
        System.out.println("inserisco 5 in posizione 0"); 
        l1.insert(0, 5);
        System.out.println("dopo:  " + l1);

        List l2 = new Cons(3, new Cons(2, new Nil()));
        System.out.println("prima: " + l2);
        l2.insert(1, -1);
        System.out.println("dopo:  " + l2);

        List l3 = new Cons(3, new Cons(2, new Nil()));
        System.out.println("prima: " + l3);
        l3.insert(0, -1);
        System.out.println("dopo:  " + l3);

        List l4 = new Cons(1, new Cons(2, new Cons(3, new Nil())));
        System.out.println("prima: " + l4);
        l4.insert(10, 9); // errore
        System.out.println("dopo:  " + l4);
    }
}