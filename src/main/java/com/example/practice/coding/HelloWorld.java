package com.example.practice.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class HelloWorld {

//    public static void main(String[] args) {
//
//        List<Integer> numbers = new ArrayList<>();
//
//        int a[] = {1, 4, 6,7, 9, 0, -10, 14};
//
//        System.out.println(" kth elemnet is : "+ getKthElement(a, 4));
//        System.out.println(" kth largest elemnet is : "+ getKthLargestElement(a, 9));
//    }

    private static int getKthElement(int a[], int k){
        int size = a.length;

        if(k>size || k<=0){
            System.out.println("k is out fo bound");
            return Integer.MAX_VALUE;
        }

        return a[k-1];
    }

    private static int getKthLargestElement(int a[], int k){
        int size = a.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        if(k>size || k<=0){
            System.out.println("k is out fo bound");
            return Integer.MAX_VALUE;
        }

//        Arrays.sort(a);

        for(int i : a){
            minHeap.add(i);

            if(minHeap.size()>k) minHeap.poll();
        }
        return minHeap.peek();
    }


}
