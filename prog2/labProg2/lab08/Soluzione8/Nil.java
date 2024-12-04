// Sottoclasse concreta (= non astratta) di List, 
// che rappresenta una lista vuota senza elementi
public class Nil extends List {
    // Non ci sono variabili private, ne serve un costruttore diverso 
    // dal costruttore di default Nil()
  
    // Ritorna true se la lista è vuota
    // Dato che Nil è sempre vuota, ritorna true.
    @Override
    public boolean empty() { 
        return true;
    }

    // Ritorna il numero di elementi nella lista.
    // Dato che Nil è sempre vuota, ritorna o
    @Override
    public int size() { 
        return 0; 
    }

    // Ritorna true se @x e' contenuto nella lista, false altrimenti.
    // Nel caso di Nil, ritorna sempre false
    @Override   
    public boolean contains(int x) { 
        return false;
    }
    
    // Aggiunge @x alla lista, mantenendo l'ordine.
    // Ritorna una nuova lista opportunamente modificata.
    // Nel caso di Nil, la nuova lista conterra' solamente l'elemento @x.
    // NOTA: Serve la classe Cons, che va compilata prima di Nil
    @Override
    public List insert(int x) { 
        // costruisci una lista che termina con l'elemento Nil (il this qui sotto)
        return new Cons(x, this);
    } 

    // Ritorna una rappresentazione testuale dell'istanza
    @Override
    public String toString() { 
        return ""; 
    }

    // Aggiungi alla lista tutti gli elementi di @l, e ritorna la nuova lista creata.
    // Nel caso di Nil, la nuova lista corrisponde con @l.
    @Override
    public List append(List l) { 
        return l; 
    }
    
    // Ritorna la somma di tutti gli elementi interi contenuti nella lista
    @Override
    public int sum() {
        return 0; 
    }
    
    // Ritorna l'elemento i-esimo della lista
    // La chiamata di questo metodo su oggetti di tipo Nil fallisce.
    @Override
    public int get(int i) {
    	assert false : "indice non contenuto nella lista";
    	return 0;
    }
    
    // Ritorna una nuova lista ottenuta incrementando di 1 tutti gli elementi.
    @Override
    public List succ() { 
        return this; 
    }
    
    // Ritorna una nuova lista contenente solo gli elementi minori o uguali ad @x
    @Override
    public List filter_le(int x){
        return this;
    }

    // Ritorna una nuova lista contenente solo gli elementi maggiori di @x
    @Override
    public List filter_gt(int x){
        return this;
    }
    
    // Ritorna una nuova lista contenente solo gli elementi in comune tra this e @l.
    @Override
    public List intersect(List l){
        return this;
    }
}
