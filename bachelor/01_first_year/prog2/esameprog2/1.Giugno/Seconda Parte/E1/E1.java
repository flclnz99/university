import java.util.*;

class E1{
    //Metodo da completare
    public static boolean ok(Node p, int[] w, int n){
        for(int i = 0; i<w.length; i++){
            if(w[i]<0){
                return false;
            }
            if(Math.sqrt(w[i])>n){
                return false;
            }
        }
        return true;
    }

    //Classe da Analizzare
    public static void h(Node p, int[] w, int n){
        for(int i = 0; i<w.length; i++){        //n = 1
            if(Math.sqrt(w[i])>n){              //w = [ 4, 6, 8 ]
                Node q = p;                     //p
                for(int j = 0;j<w[i];j++){
                    q = q.getNext();
                }
                System.out.println(q.getElem());
            }
        }
    }

    //Test
    public static String toString(Node p){
        String s = "";
        while(p != null){
            s = s+p.getElem()+" ";
            p = p.getNext();
        }
        return s;
    }
    public static String toString(int[] v){
        int i = 0, l = v.length;
        String s = "";
        while(i < l){
            s = s+v[i]+" ";
            i++;
        }
        return s;
    }

    public static Random r = new Random();

    public static Node list(int n){
        Node p = null;
        for(int i = n; i>0; i--){
            p = new Node(r.nextInt(10)-2,p);
        }
        return p;
    }

    public static void test(){
        boolean val = true;
        int n = r.nextInt(3);
        int m = r.nextInt(3);
        Node p = list (n);
        int u[] = new int[r.nextInt(5)];
        for(int i = 0; i<u.length; i++){
            u[i] = r.nextInt(3);
            u[i] = u[i]*u[i];
            if(r.nextInt(10)==0){
                u[i] = -1;
            }
            val = val && (u[i] >= 0) && ((Math.sqrt(u[i]) > m) == false || u[i] < n);
        }
        check("p = " + toString(p) + "\nv = " + toString(u) + "\nn = "+m+"\nok(p, v, "+m+")=",E1.ok(p,u,m),val);
    }

    public static void check (String s, boolean a, boolean b){
        if(a==b){
            System.out.println(s+a+" OK");
        }
        else{
            System.out.println(s+a+" EXPECTED " + b+ "\n");
        }
    }

    //Main
    public static void main(String[] args){
        for(int i = 0; i<10; i++){
            System.out.println("Test n."+i);
            test();
        }
    }
}

//Classe Nodo
class Node{
    private int elem;
    private Node next;
  
    //Costruttore
    public Node(int elem, Node next){
        this.elem=elem;
        this.next=next;
    }
  
    //Get & Set
    public int getElem(){return elem;}
    public Node getNext(){return next;}
    public void setElem(int elem){this.elem=elem;}
    public void setNext(Node next){this.next=next;}

    /* 
     //Metodo Mio
        for(int i = 0; i<w.length; i++){
            if(w[i]<0){
                return false;
            }
            if(Math.sqrt(w[i])>n){
                return false;
            }
        }
        return true;

    //Metodo Prof
        /*int numEl = 0;
        Node q = p;
        while(q != null){
            numEl++; //Conta Quanti elementi ci sono in q
            q = q.getNext();
        }
        for(int i = 0; i<w.length; i++){
            if(w[i] < 0){ return false;}
            if((Math.sqrt(w[i]) > n && w[i] >=numEl)){
                return false;
            }
        }
        return true;*/
    
}