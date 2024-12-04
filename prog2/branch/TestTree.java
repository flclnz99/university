import java.util.*;

public class TestTree{
    public static void main(String[] args) {
        Random r = new Random();
        // creo un albero con 10 numeri interi casuali
        Tree<Integer> t = new Leaf<>();
        for (int i = 0; i < 10; i++)
            t = t.insert(r.nextInt(20));
        testTreeMethods(t);

        // creo un albero come nel testo della specifica
        Tree<Integer> t1 = new Branch<Integer>(3, 
                        new Branch<Integer>(1, 
                            new Leaf<Integer>(),
                            new Branch<Integer>(2,
                                new Leaf<Integer>(),
                                new Leaf<Integer>())),
                        new Branch<Integer>(5,
                            new Leaf<Integer>(),
                            new Leaf<Integer>()));
        testTreeMethods(t1);
    }

    private static void testTreeMethods(Tree<Integer> t) {
        System.out.println("t = " + t);
        //System.out.println("t.empty() = " + t.empty());
        //System.out.println("t.max() = " + t.max());
        //System.out.println("t.contains(6) = " + t.contains(6));
        //System.out.println("t.depth() = " + t.depth());
        // aggiungere le chiamate ai metodi da implementare
        //System.out.println("t.m() = " + t.m(1,5));
        t.g(0,3);
        System.out.println("t.m() = " + t);
    }
}
