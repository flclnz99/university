public class Nil extends List {
    public List insert(int n, int x) {
        //assert n==0: "valore non valido";
        return new Cons(x,this);
    }

    public String toString() {
        return "";
    }
}