import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue<T> implements AbstractQueue<T>{
  ArrayList<T> minHeap=null;
  Comparator<? super T> comparator=null;

  public PriorityQueue(Comparator<? super T> comparator) throws PriorityQueueException{
    if(comparator==null) throw new PriorityQueueException("Priority Queue: comparator is null");
    this.minHeap = new ArrayList<>();
    this.comparator = comparator;
  }

  private int right(int i) {
    return 2*i+2;
  }

  private int left(int i) {
    return 2*i+1;
  }

  private int parent(int i) {
    if (i%2 == 1) {
        return i/2;
    }
    return (i-1)/2;
  }

  public boolean empty(){
    return (this.minHeap).isEmpty();
  }

  public T top(){
    return (this.minHeap).get(0);
  }

  private void swap(int i, int parent) {
    T tmp = (this.minHeap).get(parent);
    (this.minHeap).set(parent, (this.minHeap).get(i));
    (this.minHeap).set(i, tmp);
  }

  private void minHeapify(int i) {
    int left = left(i);
    int right = right(i);
    int smallest = -1;
    // find the smallest key between current node and its children.
    if (left <= (this.minHeap).size() - 1 &&  (this.comparator).compare((this.minHeap).get(left),(this.minHeap).get(i))<0) {
        smallest = left;
    } else {
        smallest = i;
    }
    
    if (right <= (this.minHeap).size() - 1 && (this.comparator).compare((this.minHeap).get(right),(this.minHeap).get(smallest))<0) {
        smallest = right;
    }
    // if the smallest key is not the current key then bubble-down it.
    if (smallest != i) {
        swap(i, smallest);
        minHeapify(smallest);
    }
  }

  public boolean push(T item) {
    (this.minHeap).add(item);
    int i = (this.minHeap).size() - 1;
    int parent = parent(i);
    while (parent != i && (this.comparator).compare((this.minHeap).get(i),(this.minHeap).get(parent))<0) {
        swap(i, parent);
        i = parent;
        parent = parent(i);
    }
    return true;
  }

  public void pop() {
    if ((this.minHeap).size() == 0) {
        throw new IllegalStateException("MinHeap is EMPTY");
    }

    T lastItem = (this.minHeap).remove((this.minHeap).size() - 1);
    
    if((this.minHeap).size() > 0){
      (this.minHeap).set(0, lastItem);
      minHeapify(0); 
    }
  }

  public boolean contains(T e){
    if ((this.minHeap).size() == 0) {
      throw new IllegalStateException("MinHeap is EMPTY");
    }
    if((this.minHeap).contains(e)){
      return true;
    } else {
      return false;
    }
  }

  public int size(){
    return (this.minHeap).size();
  }

  public T get(int i){
    return (this.minHeap).get(i);
  }
  
  public void print(){
    for(int i=0; i<(this.minHeap).size(); i++){
      System.out.println((this.minHeap).get(i) + "  " + i);
    }
  }
}
