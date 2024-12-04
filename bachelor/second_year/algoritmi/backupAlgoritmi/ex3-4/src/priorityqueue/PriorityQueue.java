package priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class PriorityQueue<T> implements AbstractQueue<T>{
    ArrayList<T> minHeap;
    Comparator<? super T> comparator;
    private HashMap<T, Integer> indexMap;

    public PriorityQueue(Comparator<? super T> comparator) throws PriorityQueueException{
        if(comparator==null) throw new PriorityQueueException("Priority Queue: comparator is null");
        this.minHeap = new ArrayList<>();
        this.comparator = comparator;
        this.indexMap = new HashMap<>();
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
/*
    private void swap(int i, int parent) {
        T tmp = (this.minHeap).get(parent);
        (this.minHeap).set(parent, (this.minHeap).get(i));
        (this.minHeap).set(i, tmp);
    }
*/

    private void swap(int i, int parent) {
        T tmp = minHeap.get(parent);
        minHeap.set(parent, minHeap.get(i));
        minHeap.set(i, tmp);
        indexMap.put(minHeap.get(parent), parent);
        indexMap.put(minHeap.get(i), i);
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
/*
    public boolean push(T item) {
        //se item è gia' presente lanciamo eccezione
        if(this.minHeap.size()==0){
            this.minHeap.add(0, item);
            return true;
        }
        if(contains(item))
            return false;
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
*/

    public boolean push(T item) {
        if (indexMap.containsKey(item)) {
            return false; // L'elemento è già presente nella coda
        }

        minHeap.add(item);
        int i = minHeap.size() - 1;
        int parent = parent(i);
        while (parent != i && comparator.compare(minHeap.get(i), minHeap.get(parent)) < 0) {
            swap(i, parent);
            i = parent;
            parent = parent(i);
        }

        indexMap.put(item, i);
        return true;
    }


    /*
    public T pop() {
        if ((this.minHeap).size() == 0) {
            throw new IllegalStateException("MinHeap is EMPTY");
        }

        T firstItem = (this.minHeap).remove(0);

        if((this.minHeap).size() > 0){
            (this.minHeap).add(0, (this.minHeap).remove((this.minHeap).size() - 1));
            minHeapify(0);
        }
        return firstItem;
    }
    */

    public T pop() {
        if (minHeap.isEmpty()) {
            throw new IllegalStateException("MinHeap is EMPTY");
        }

        T firstItem = minHeap.get(0);
        indexMap.remove(firstItem);

        if (minHeap.size() > 1) {
            T lastItem = minHeap.remove(minHeap.size() - 1);
            minHeap.set(0, lastItem);
            indexMap.put(lastItem, 0);
            minHeapify(0);
        } else {
            minHeap.clear();
        }

        return firstItem;
    }

    /*
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
    */


    public boolean contains(T item) {
        return indexMap.containsKey(item);
    }

    public int size(){
        return (this.minHeap).size();
    }

    public T get(int i){
        return (this.minHeap).get(i);
    }

    public void print(){
        for(int i=0; i<(this.minHeap).size(); i++){
            System.out.println((this.minHeap).get(i) + "  index=" + i);
        }
    }


    public T remove(T item) {
        Integer index = indexMap.get(item);
        if (index == null) {
            return null; // L'elemento non è presente nella coda
        }
        T element;
        if (index == minHeap.size() - 1) {
            // L'elemento da rimuovere è l'ultimo nella coda
            element = minHeap.remove((int) index);
            indexMap.remove(item);
        } else {
            T lastItem = minHeap.remove(minHeap.size() - 1);
            element=minHeap.get(index);
            minHeap.set(index, lastItem);
            indexMap.put(lastItem, index);
            indexMap.remove(item);

            int parent = parent(index);
            if (index > 0 && comparator.compare(minHeap.get(index), minHeap.get(parent)) < 0) {
                // L'elemento da rimuovere è minore del padre, spostalo verso l'alto
                while (index > 0 && comparator.compare(minHeap.get(index), minHeap.get(parent)) < 0) {
                    swap(index, parent);
                    index = parent;
                    parent = parent(index);
                }
            } else {
                // L'elemento da rimuovere è maggiore o uguale al padre, esegui minHeapify verso il basso
                minHeapify(index);
            }
        }
        return element;
    }
}
