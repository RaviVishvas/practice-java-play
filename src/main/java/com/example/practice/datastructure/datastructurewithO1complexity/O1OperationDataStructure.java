package com.example.practice.datastructure.datastructurewithO1complexity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class O1OperationDataStructure {

    private int capacity = 100000; //defualt
    private int pointer = 0;
    private int arr[];

    private Map<Integer, Integer> mapping = new HashMap<>();

    public O1OperationDataStructure() {
        this.arr = new int[this.capacity];
    }

    public O1OperationDataStructure(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
    }


    public synchronized void insert(int ele){
        if(pointer>=capacity) System.out.println("Can't push more element, capacity is full!");
        if(mapping.containsKey(ele)){
            System.out.println("Element already present...");
        } else {
            arr[pointer] = ele;
            mapping.put(ele, pointer);
            pointer++;
        }
    }

    public synchronized void delete(int ele){
        if(!mapping.containsKey(ele)){
            System.out.println("This element is not present....");
        }
        else {
            int toDelete = mapping.get(ele);
            int lastElement = arr[pointer-1];
            arr[toDelete] = lastElement;
            pointer--;

            mapping.put(lastElement, toDelete);
            mapping.remove(ele);
        }
    }

    public int getRandom(){

        if(pointer==0){
            System.out.println("data structure does not has any value yet, please first insert");
            return Integer.MIN_VALUE;
        }

         long random = getRandomNumber();
         return arr[(int)random%pointer];
    }

    private long getRandomNumber(){

        long seed = new Random().nextInt();
        long a = 37263l, b = 638726798l;

        return (seed*a)%pointer + b;
    }

}
