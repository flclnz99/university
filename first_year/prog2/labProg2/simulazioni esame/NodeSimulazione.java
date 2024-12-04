
// SIMULAZIONE DI COMPITO DI ESAME DI LABORATORIO DI PROGII
// MAGGIO 2022

// Definite un metodo statico
// int contaMinoriUguali(Node p, Node q)
// che conti in quante posizioni della lista p il valore di p e' <= del valore 
// della lista q nella stessa posizione. Le lunghezze di p,q possono essere 
// diverse. E' consentita sia una soluzione ricorsiva che una iterativa (con cicli)
public class NodeSimulazione {

    public static String toString(Node p) {
        //Metodo statico di conversione a stringa, perche' siamo fuori di Node
        if (p==null) 
            return ""; 
        else 
            return p.getElem() + "\t" + toString(p.getNext());
    }

    public static int contaMinoriUguali(Node p, Node q) {
      if(p==null||q==null){
        return 0;
      }
      if((p.getElem()<=q.getElem())){
          return 1+contaMinoriUguali(p.getNext(), q.getNext());
      } else {
        return contaMinoriUguali(p.getNext(), q.getNext());
      }
    }

    public static void check(String espr, int trovato, int atteso) {
        assert trovato==atteso: espr + " vale " + trovato + " valore atteso " + atteso; 
        System.out.println("OK " + espr + "\t=\t" + atteso);
    }

    public static void main(String[] args) {
        //main di prova per int contaMinoriUguali(Node p, Node q)
        // eseguire con le asserzioni abilitate (-ea)
        Node p=new Node(1,new Node(2,new Node(3,null)));
        Node q=new Node(3,new Node(2,new Node(1,null)));
        Node r=new Node(1,new Node(1,new Node(1,null)));
        Node s=new Node(10,new Node(20,null));

        System.out.println("p\t=\t" + toString(p));
        System.out.println("q\t=\t" + toString(q));
        System.out.println("r\t=\t"  + toString(r));
        System.out.println("s\t=\t"  + toString(s) + "\n");

        check("contaMinoriUguali(s,p)",contaMinoriUguali(s,p),0);
        check("contaMinoriUguali(p,r)",contaMinoriUguali(p,r),1);
        check("contaMinoriUguali(p,q)",contaMinoriUguali(p,q),2);
        check("contaMinoriUguali(p,s)",contaMinoriUguali(p,s),2); 
        check("contaMinoriUguali(r,p)",contaMinoriUguali(r,p),3);
        check("contaMinoriUguali(p,p)",contaMinoriUguali(p,p),3);
        check("contaMinoriUguali(p,null)",contaMinoriUguali(p,null),0);
        check("contaMinoriUguali(null,q)",contaMinoriUguali(null,q),0);
        check("contaMinoriUguali(null,null)",contaMinoriUguali(null,null),0); 

        System.out.println("\nOK: Tutti i valori trovati sono i valori attesi");
    }
}