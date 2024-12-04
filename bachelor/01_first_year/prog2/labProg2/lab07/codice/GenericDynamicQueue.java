public class GenericDynamicQueue<T> {

  private GenericNode<T> first;
  private GenericNode<T> last;

  public GenericDynamicQueue() {
      first = last = null;
  }

  public void scriviOutput() {
      GenericNode<T> node = first;
      while (node != null) {
          System.out.print(node.getElem() + " "); 
          node = node.getNext();
      }
      System.out.println();
  }

  public void enqueue(T x) {
      // crea un nuvo nodo da inserire in fondo
      GenericNode<T> node = new GenericNode<>(x, null);
      if (empty()) // coda vuota
          first = last = node;
      else { // almeno un elemento
          last.setNext(node); // last -> node -> null
          last = node;
      }
      assert first!=null && last!=null && last.getNext()==null;
  }

  public T dequeue() {
      assert !empty() : "non si puo' chiamare dequeue su una coda vuota.";
      T x = first.getElem();
      first = first.getNext();
      if (first == null)
          last = null;

      assert ((first==null && last==null) || 
              (first!=null && last!=null && last.getNext()==null));
      return x;
  }

  public boolean empty() {
      return first == null;
  }

  public T front() {
      assert !empty() : "non si puo' chiamare front su una coda vuota.";
      return first.getElem();
  }

  // ritorna la dimensione della coda
  public int size() {
      int n = 0;
      for (GenericNode<T> p = first; p != null; p = p.getNext())
          n++;
      return n;
  }

  // ritorna true se @x e' contenuto nella coda, false altrimenti
  public boolean contains(T x) {
      for (GenericNode<T> p = first; p != null; p = p.getNext())
          if (p.getElem() == x) 
              return true; 
      return false;
  }

}