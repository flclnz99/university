// Un esempio di classe astratta: List
// Liste ordinate di interi per rappresentare insiemi
// INVARIANTE della classe: 
//   in ogni lista gli elementi sono in ordine
// Le sottoclassi Nil, Cons realizzano queste operazioni nei vari casi:
//   Nil: nel caso di una lista con zero elementi (vuota)
//   Cons: nel caso di una lista con almeno un elemento
public abstract class List {
    public abstract boolean empty();

    public abstract int size();

    public abstract boolean contains(int x);

    public abstract List insert(int x);

    public abstract List append(List l);

    public abstract int sum();

    public abstract int get(int i);

    public abstract List succ();

    public abstract List filter_le(int x);

    public abstract List filter_gt(int x);

    public abstract List intersect(List l);
}
