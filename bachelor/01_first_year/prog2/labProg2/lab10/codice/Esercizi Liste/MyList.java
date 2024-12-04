// MyList.java
public class MyList {
    private Node first; // Riferimento al primo nodo della lista

    public MyList() {
        this.first = null;
    }

    public void insert(int elem) {
        first = new Node(elem, first);
    }

    public void modifica(){
        int somma = 0;
        Node p = first;     
        while(p!=null){
            somma+=p.getElem();
            p.setElem(somma);
            p=p.getNext();
        }    
    }

    public void pushSomma(){
        if(first==null){
            insert(0);
        } else {
            Node p=first;
            int acc=0;
            while(p!=null){
                if(p.getElem()>=0){
                    acc+=p.getElem();
                }
                p=p.getNext();
            }
            this.insert(acc);
        }
    }

    public void l(int v){
        Node tmp=first;
        while(tmp!=null){
            if(tmp.getElem()<v){
                this.insert(tmp.getElem());
                tmp.setElem(-100);
            }
            tmp=tmp.getNext();
        }
    }

    public String toString() {
        String res = "";
        for (Node p = first; p != null; p = p.getNext()) {
            res += p.getElem();
            if (p.getNext() != null) res += ", ";
        }
        return res;
    }
}
