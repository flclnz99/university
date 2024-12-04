public class Set<T> {
  GenericNode<T> first;
  int size;

  public Set(){
    first=null;
    size=0;    
  }

  public int size(){return size;}
  
  public boolean empty(){
    return this.first==null;
  }

  public void add(T elem){
    if(!contains(elem)){
      this.first=new GenericNode<>(elem, first);
      size+=1;
    }
  }

  public boolean contains(T elem){
    GenericNode<T> tmp=this.first;
    while(tmp!=null){
      if(tmp.getElem().equals(elem)){  //return true, elemento trovato
        return true;
      } else {
        tmp=tmp.getNext();    //passo al nodo successivo
      }
    }
    return false; //first==null, coda vuota || elemento non trovato
  }

  public boolean remove(T elem){
    if(empty()){return false;}  //lista vuota
    if(contains(elem)){         //lista non vuota che contiene elem
      GenericNode<T> tmp=first;
      GenericNode<T> prev=null;
      while(tmp!=null&&!(tmp.getElem().equals(elem))){
        prev=tmp;
        tmp=tmp.getNext();
      }
      if(prev==null){ //l'elemento è il primo della lista
        first=tmp.getNext();
        size-=1;
        return true;
      } else {
        prev.setNext(tmp.getNext());
        size-=1;
        return true;
      }
    }
    return false; //l'elemento non è presente nell'insieme
  }

  public boolean subsetOf(Set<T> s){  //ritorna true se tutti gli elementi dell'insieme sono contenuti in s
    if(s.size<this.size){
      return false;
    }
    GenericNode<T> tmp=first;
    while(tmp!=null){
      if(!(s.contains(tmp.getElem()))){
        return false;
      }
      tmp=tmp.getNext();
    }
    return true;
  }

  public Set<T> union(Set<T> s){
    while(s.first!=null){
      if(!this.contains(s.first.getElem())){
        this.first=new GenericNode<>(s.first.getElem(), this.first);
      }
      s.first=s.first.getNext();
    }
    return this;
  }

  public Set<T> intersection(Set<T> s){
    Set<T> inter=new Set<>();
    while(s.first!=null){
      if(this.contains(s.first.getElem())){
        inter.add(s.first.getElem());
      }
      s.first=s.first.getNext();
    }
    return inter;
  }

  public void print(){
    GenericNode<T> tmp=this.first;
    while(tmp!=null){
      System.out.println(tmp.getElem()+" ");
      tmp=tmp.getNext();
    }
  }
}
