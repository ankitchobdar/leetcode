package com.project;

import java.util.Arrays;

public class Solution383 {
    public static void main(String[] args) {
        System.out.println(canConstruct("aa", "aab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = getSignature(ransomNote);
        int[] arr2 = getSignature(magazine);

        for (int i = 0; i < arr.length; i++) {
            if(arr2[i] < arr[i]){
                return false;
            }
        }
        return true;
    }

    public static int[] getSignature(String input) {
        int[] output = new int[26];
        char[] arr = input.toCharArray();
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++){
            output[arr[i] - 'a']++;
        }
        return output;
    }
}
