// Nodo per realizzare liste concatenate di elementi generici.
public class Node<T> {
  private T elem;
  private Node<T> next;

  public Node(T elem, Node<T> next) {
      this.elem = elem;
      this.next = next;
  }

  public T getElem() {
      return elem;
  }

  public Node<T> getNext() {
      return next;
  }

  public void setElem(T elem) {
      this.elem = elem;
  }

  public void setNext(Node<T> next) {
      this.next = next;
  }   

  public void l(int v){
    
  }
}