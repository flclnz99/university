package org.prog3.generics.main;

import org.prog3.generics.model.Queue;

import java.util.*;

public class MainClass {
    public static void main(String[] args){
        List<Integer> AList = new ArrayList<Integer>();
        Queue<Integer> queue = new Queue<Integer>(AList);

        queue.enqueue(1);
        queue.enqueue(3);
        queue.print();

        queue.dequeue();
        queue.print();
    }
}
