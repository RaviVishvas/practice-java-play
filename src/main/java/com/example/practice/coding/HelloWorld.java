package com.example.practice.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class HelloWorld {

//    public static void solve(int arr[], int idx, int n, List<Integer> temp, List<List<Integer>> ans){
//        if(idx >= n){
//            ans.add(temp);
//            return;
//        }
//
//        for(int i= idx; i<n; i++){
//            solve(arr, i+1, n, temp, ans);
//            temp.add(arr[i]);
//
//
//            solve(arr, i+1, n, temp, ans);
//            temp.remove(i);  // index
//
//        }
//    }
//
//    public static void main(String[] args) {
//        // System.out.println("Start small. Ship something.");
//
//        int arr[] = new int[]{1, 2, 3};
//
//        List<Integer> temp = new ArrayList();
//
//        List<List<Integer>> ans = new ArrayList();
//
//        solve(arr, 0, arr.length, temp, ans);
//
//        for (List<Integer> ele : ans){
//            System.out.println(" Ans array is : "+ ans);
//        }
//    }

    public static void main(String[] args) {
        int cir[] = new int[]{2, 7, 9, 3, 1};
        System.out.println("Output is ===> " + maxCircularSum(cir));
    }

    public static int maxCircularSum(int[] values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        if (values.length == 1) {
            return values[0];
        }

        // In a circle, the first and last elements cannot both be selected.
        int excludeFirst = maxLinearSum(values, 1, values.length - 1);
        int excludeLast = maxLinearSum(values, 0, values.length - 2);
        return Math.max(excludeFirst, excludeLast);
    }

    private static int maxLinearSum(int[] values, int start, int end) {
        int previousTwo = 0;
        int previousOne = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(previousOne, previousTwo + values[i]);
            previousTwo = previousOne;
            previousOne = current;
        }

        return previousOne;
    }
}







