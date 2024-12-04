package org.prog3.generics.model;

import java.util.List;
import java.util.ListIterator;

public class Queue<T>{
    private List<T> queue;

    public Queue(List<T> queue) {
        this.queue = queue;
    }

    public boolean isEmpty(){
        return queue.size() == 0;
    }

    public boolean isType(T elem){
        return elem.getClass() == queue.get(0).getClass();
    }

    public void enqueue(T elem){
        assert isType(elem): "incorrect type of element";
        queue.add(0,elem);
    }

    public T dequeue(){
        assert isEmpty(): "the queue is empty";
        //System.out.println(queue.size());
        T elem = queue.remove(queue.size()-1);
        return elem;
    }

    public void print(){
        ListIterator<T> listIterator = queue.listIterator();

        while(listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
}
