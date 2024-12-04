// classe per rappresentare una diramazione

class Branch<T extends Comparable<T>> extends Tree<T>{
    private T elem;
    private Tree<T> left;
    private Tree<T> right;

    public Branch(T elem, Tree<T> left, Tree<T> right) {
        this.elem = elem;
        this.left = left;
        this.right = right;
    }

    public boolean empty() {
        return false;
    }

     

    public boolean contains(T x) {
        if (x.compareTo(elem)==0)
            return true;
        else if (x.compareTo(elem)<0)
            return left.contains(x);
        else
            return right.contains(x);
    }

    public Tree<T> insert(T x) {
        if (x.compareTo(elem)<0)
            left = left.insert(x);
        else if (x.compareTo(elem)>0)
            right = right.insert(x);
        return this;
    }

    public T max() {
        return right.empty() ? elem : right.max();
    }

    public Tree<T> remove(T x) {
        if (x == elem) // trovato elemento da eliminare
            if (left.empty())
                // il sottoalbero sinistro e` vuoto, dunque resta il
                // sottoalbero destro
                return right;
            else if (right.empty())
                // il sottoalbero destro e` vuoto, dunque resta il
                // sottoalbero sinistro
                return left;
            else {
                // entrambi i sottoalberi sono non vuoti e dobbiamo
                // individuare un elemento da collocare alla radice
                // dell'albero dopo l'eliminazione di elem. Scegliamo
                // il massimo elemento del sottoalbero sinistro, il
                // quale sara` per definizione piu` grande di tutti
                // gli altri elementi del sottoalbero sinistro e, per
                // la proprieta` degli alberi binari di ricerca, piu`
                // piccolo di tutti gli elementi nel sottoalbero
                // destro. In alternativa sarebbe stato possibile
                // scegliere il minimo elemento del sottoalbero destro
                elem = left.max();
                // eliminiamo l'elemento massimo dal sottoalbero
                // sinistro
                left = left.remove(elem);
                return this;
            }
        else if (x.compareTo(elem)<0) {
            // se c'e`, l'elemento da eliminare e` nel sottoalbero
            // sinistro
            left = left.remove(x);
            return this;
        } else {
            // se c'e`, l'elemento da eliminare e` nel sottoalbero
            // destro
            right = right.remove(x);
            return this;
        }
    }
    
    public Tree<T> m(T x, T z){
        if(this.left!=null && this.right!=null){
            if((x.equals(elem)) || (elem.compareTo(z)>0)){
                Tree<T> tmp = this.left;
                this.left=this.right;
                this.right=tmp;
            } else {
                left.m(x,z);
                right.m(x,z); 
            }
        } else if(this.left==null || this.right!=null){
            right.m(x,z);
        } else if(this.right==null || this.left!=null){
            left.m(x,z);
        }
        return this;
    }

    public void g(T z, T x){
        if(this.left!=null && this.right!=null){
            if(x.equals(elem)){
                this.elem=z;
            } else {
                this.left.g(x,z);
                this.right.g(x,z); 
            }
        } else if(this.left==null || this.right!=null){
            this.right.g(x,z);
        } else if(this.right==null || this.left!=null){
            this.left.g(x,z);
        }
    }

    public int depth() {
        return 1 + Math.max(left.depth(), right.depth());
    }

    public String toString() {
        return "Branch(" + elem + "," + left + "," + right + ")";
    }
}
