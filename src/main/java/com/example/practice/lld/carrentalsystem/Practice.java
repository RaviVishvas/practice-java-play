package com.example.practice.lld.carrentalsystem;

import java.util.HashMap;
import java.util.Map;

public class Practice {

    public static void main(String[] args) {
        String str = "aaabbydccchhc";

//        a3b2ydc2h2c

        String compressedStr = compressString(str);

        System.out.println("Compressd String ===> "+compressedStr);

    }

    static String compressString(String str){

        Map<Character, Integer> charFreq = new HashMap<>();

        for(char ch : str.toCharArray()){
            charFreq.put(ch, charFreq.getOrDefault(ch, 0)+1);
        }

        char lastChar=' ';
        String finalStr="";

        for(int i=0; i<str.length(); i++){
            char currChar = str.charAt(i);
            if(currChar!=lastChar){
                finalStr+=currChar;
                finalStr+=charFreq.get(currChar);
                lastChar = currChar;
            }
        }

        return finalStr;
    }
}
