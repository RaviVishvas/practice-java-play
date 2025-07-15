package com.example.practice.datastructure.LRUcache;

public class Node {

    Object val;
    Node next;
    Node prev;

    Node(Object val){
        this.val = val;
        next = null;
        prev = null;
    }
}
