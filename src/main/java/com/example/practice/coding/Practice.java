package com.example.practice.coding;

import java.util.*;

public class Practice {


    public static void main(String[] args) {
        String arr[]={"eat", "tea", "tan", "ate", "nat", "bat"};

        Map<String, List<String>> map = new HashMap<>();

        for(String ele : arr){

            char[] chArr = ele.toCharArray();
            Arrays.sort(chArr);

            String sorted = String.copyValueOf(chArr);
            System.out.println(sorted);

            if(map.containsKey(sorted)){
                List<String> list = map.get(sorted);
                list.add(ele);
                map.put(sorted, list);
            } else{
                List<String> list = new ArrayList();
                list.add(ele);
                map.put(sorted, list);
            }
        }

        List<List<String>> ans = new ArrayList<>();

        for(Map.Entry<String, List<String>> value : map.entrySet()){
            ans.add(value.getValue());
        }

        System.out.println(ans);
    }


//    public static void main(String[] args) {
//        int arr[] = {1,2,0,-1,4,8,9,0,11,0};
//
//        int z=-1;
//        for(int i=0; i<arr.length; i++){
//            if(arr[i]==0){
//                z=i;
//                break;
//            }
//        }
//
//        for(int i=z+1; i<arr.length; i++ ){
//            if(arr[i]!=0){
//                    int temp = arr[i];
//                    arr[i] = arr[z];
//                    arr[z]= temp;
//                    z++;
//            }
//        }
//
//        for(int e : arr)
//        System.out.println(e);
//    }



}
