package com.example.practice.datastructure.LRUcache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    int capacity = 1000; // default
    int size = 0;
    Node head, tail;
    Map<Object, Node> mapping = new HashMap();

    LRUCache() {
        head = new Node(null);
        tail = new Node(null);

        head.next = tail;
        tail.prev = head;

        System.out.println("Cache capacity is : " + capacity);
    }

    LRUCache(int capacity) {
        if (capacity < 1) throw new RuntimeException("Capacity should be greater than 0..");
        this.capacity = capacity;

        System.out.println("Cache capacity is : " + capacity);

        head = new Node(null);
        tail = new Node(null);

        head.next = tail;
        tail.prev = head;
    }

    public synchronized void put(Object val) {
        if (mapping.containsKey(val)) {
            Node nod = mapping.get(val);
            if (tail == nod) tail = tail.prev;
            remove(nod);
            nod.val = val;
            insert(nod);
        } else {
            Node newNod = new Node(val);
            insert(newNod);
            mapping.put(val, newNod);

            if (size == capacity) {
                Node toDelete = tail.prev;

                remove(toDelete);
                mapping.remove(toDelete.val);
            } else {
                size++;
            }
        }
    }

    public synchronized Object get(Object val) {
        if (!mapping.containsKey(val)) {
            return "Key not Found!";
        } else {
            Node nod = mapping.get(val);

            remove(nod);
            insert(nod);

            return nod.val;
        }
    }

    private void insert(Node nod) {
        nod.next = head.next;
        head.next.prev = nod;

        nod.prev = head;
        head.next = nod;
    }

    private Node remove(Node nod) {
        nod.next.prev = nod.prev;
        nod.prev.next = nod.next;

        return nod;
    }
}
