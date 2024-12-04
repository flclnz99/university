// classe astratta che definisce le operazioni su alberi binari
public abstract class Tree<T extends Comparable<T>> {
    public abstract boolean empty();
    public abstract T max();
    public abstract boolean contains(T x);
    public abstract Tree<T> insert(T x);
    public abstract Tree<T> remove(T x);
    public abstract int depth();
    public abstract Tree<T> m(T x, T z);
    public abstract void g(T z, T x);
}
