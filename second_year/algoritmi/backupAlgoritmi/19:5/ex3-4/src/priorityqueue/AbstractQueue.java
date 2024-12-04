public interface AbstractQueue<E> {
  public boolean empty(); // controlla se la coda è vuota
  public E top(); // accede all'elemento in cima alla coda
  public boolean push(E e); // aggiunge un elemento alla coda
  public void pop(); // rimuove l'elemento in cima alla coda
  public boolean contains(E e); //restituisce true se la coda contiene l'elemento
  //public void increasePriority(E e1, E e2); //e1 è un elemento presente nella coda; e2 è un elemento che può essere uguale a e1 (ma non ad 
    //altri elementi della coda); la priorità di e2 è più alta di quella di e1. Il metodo sostituisce e2 a e1 nella coda (e, 
    //nel caso, riposiziona correttamente e2). In generale, il metodo sostituisce l'elemento e1, con un elemento e2 avente 
    //priorità maggiore, garantendo che, dopo la sostituzione, nella coda continuino a non esserci elementi ripetuti. 
    //Se e1.equals(e2) è vero, allora, in pratica, il metodo realizza un incremento della priorità di e1.
};
