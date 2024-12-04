public abstract class Lista {
 
  public abstract boolean empty();

  public abstract int getElem();  

  public abstract Lista aggiungi(int x);

  public abstract Lista rimuoviTesta();

  public abstract void accetta(Visitatore visitatore);
  
}
