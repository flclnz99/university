public class Set<T> {
    private Node<T> first;
    private int size;

    public Set() {
		this.first = null;
		this.size = 0;
    }

    public int size() { 
    	return this.size;
    }

    public boolean empty() { 
    	return this.first == null;
    }

    public void add(T elem) {
			if (!contains(elem)) {
					this.first = new Node<T>(elem, first);
					this.size++;
			}
    }

    // rimuove l'elemento @elem dall'insieme, se esiste.
    // Se @elem non appartiene all'insieme, il metodo non ha effetto.
    public boolean remove(T elem) {
		// rimuovere un elemento dall'insieme significa scollegare il
		// nodo che lo contiene dalla lista concatenata che inizia con
		// first. Per questo motivo, e` necessario individuare il nodo
		// che contiene l'elemento (se c'e`) e ricordarsi al tempo
		// stesso il riferimento al nodo immediatamente precedente,
		// cosi` da poter agire sul suo campo next

		Node<T> p = first;
		// la variabile prev mentiene un riferimento al nodo
		// precedente a p. All'inizio della scansione, quando p e`
		// uguale a first, non c'e` alcun nodo "precedente" a p, per
		// cui usiamo il riferimento non valido null come valore
		// iniziale per prev
		Node<T> prev = null;
		// ricerca di elem nell'insieme
		while (p != null && !p.getElem().equals(elem)) {
		    prev = p;
		    p = p.getNext();
		}

		// il ciclo puo` essere terminato per due motivi. O p e`
		// diventato null, il che significa che abbiamo terminato la
		// scansione sulla lista senza trovare elem, oppure p != null
		// e p.getElem() == elem, dunque p e` un riferimento al nodo che
		// contiene elem (e prev un riferimento al nodo immediatamente
		// precedente). 
		if (p == null)
		    return false; // l'elemento non e' presente nell'insieme
		else if (prev != null) {
			// l'elemento non e' il primo: scollega il nodo e aggiorna il 
			// nodo predecessore
		    prev.setNext(p.getNext());
		    this.size--;
		    return true;
		} 
		else { // l'elemento e' il primo dell'insieme
		    this.first = p.getNext();
		    this.size--;
		    return true;
		}
    }

    // ritorna true se l'elemento @elem e' presente nell'insieme
    public boolean contains(T elem) {
		// esegui una scansione lineare di tutti gli elementi dell'insieme
		Node<T> p = this.first;
		while (p != null) {
			if(p.getElem().equals(elem))
				return true;
			p = p.getNext();
		}
		return false;
    }

    // ritorna true se tutti gli elementi dell'insieme sono contenuti in @s
    public boolean subsetOf(Set<T> s) {
    	if (this.size() > s.size())
    		return false; // this non puo` essere contenuto in s.

		// E' sufficiente trovare un elemento di x che NON sia contenuto
		// in y per stabilire che x NON e` un sottoinsieme di y.
		Node<T> p = this.first;
		while (p!=null) {
		    if (!s.contains(p.getElem()))
				return false;
	        p = p.getNext();
	    }
		return true;
    }

    // ritorna true se this e @s contengono gli stessi elementi
    public boolean equalsTo(Set<T> s) {
		// this ed s sono uguali se e solo se hanno la stessa
		// dimensione ed uno e` sottoinsieme dell'altro
		return this.size() == s.size() && this.subsetOf(s);
    }

    // ritorna un nuovo insieme formato dall'unione di tutti gli elementi
    // dell'insieme this e di @s.
    public Set<T> union(Set<T> s) {
		Set<T> result = new Set<>();
		// aggiungo a result tutti gli elementi di this
        Node<T> p = this.first;
		while (p != null) { 
			result.add(p.getElem());
			p = p.getNext();
        }
		// aggiungo a result tutti gli elementi di s
        p = s.first;
        while (p != null) { 
            result.add(p.getElem());
            p = p.getNext();
        }
        return result;
    }

    // ritorna un nuovo insieme contenente gli elementi in comune 
    // tra l'insieme this e @s.
    public Set<T> intersection(Set<T> s) {
        Set<T> result = new Set<>();
        // aggiungo a result tutti gli elementi di this che sono
        // contenuti anche in s
        Node<T> p = this.first;
        while (p != null) {
            if (s.contains(p.getElem())) 
                result.add(p.getElem());
            p = p.getNext();
        }
        return result;
    }

    // stampa gli elementi dell'insieme
    public void print() {
        Node<T> p = this.first;
        System.out.print("{ ");
        while (p != null) {
            System.out.print(p.getElem() + " ");
            p = p.getNext();
        }
        System.out.println("}");
    }
}
