package com.example.practice.datastructure.LRUcache;

public class Main {

    public static void main(String[] args) {
        LRUCache cacheMaxCapacity = new LRUCache();
        LRUCache cache = new LRUCache(2);

        System.out.println("Put 10 : ");
        cache.put("10");
        System.out.println("Put 12 : ");
        cache.put("12");
        System.out.println("Get 12 : "+cache.get("12"));

        System.out.println("Get 10 : "+cache.get("10"));

        System.out.println("Put 22 : ");
        cache.put("22");

        System.out.println("Get 12 : "+cache.get("12"));


        System.out.println("Get 10 : "+cache.get("10"));

        LRUCache cache1 = new LRUCache(0);

    }
}
