package com.example.practice.coding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {

    public static void main(String[] args) {
        String arr[] = {"eat","tea","tan","ate","nat","bat"};

        List<List<String>> ans = Arrays.stream(arr).collect(Collectors.groupingBy(e-> {
            char temp[] = e.toCharArray();
            Arrays.sort(temp);
            return String.valueOf(temp);
        })).values().stream().toList();


        System.out.println("====> "+ ans);

    }
}
