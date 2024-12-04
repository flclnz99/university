// Implementazione della classe Leaf per rappresentare alberi vuoti

class Leaf<T extends Comparable<T>> extends Tree<T>{
    public Leaf() { }

    public boolean empty() {
        return true;
    }

    public T max() {
        // e` illegale invocare l'operazione max sull'albero vuoto...
        assert false;
        // ...tuttavia il compilatore mi costringe a ritornare
        // comunque un valore perche' il metodo ha tipo di ritorno
        // T
        return null;
    }

    public boolean contains(T x) {
        return false;
    }

    public Tree<T> insert(T x) {
        return new Branch<T>(x, this, this);
    }

    public Tree<T> remove(T x) {
        return this;
    }

    // realizziamo anche un metodo toString per stampare un albero
    // binario sul terminale
    public String toString() {
        return "Leaf";
    }

    public Tree<T> m(T x, T z){
        return this;
    }

    public void g(T z, T x){}

    public int depth() {
        return 0;
    }
}
