package com.project.sorting;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 11, 13, 15, 6};
        int[] output = countingSort(arr);
        for (int num : output) {
            System.out.print(num + " ");
        }
    }

    public static int[] countingSort(int[] arr) {
        // Solution-1
//        int[] output = new int[arr.length];
//        //Find max,min from arr
//        int min = arr[0];
//        int max = arr[0];
//        for (int k : arr) {
//            if (k < min) {
//                min = k;
//            }
//            if (k > max) {
//                max = k;
//            }
//        }
//        //create new countarray of max-min+1 size
//        int[] cntArry = new int[max - min + 1];
//        //Populate count of occurrence of each unique element
//        for (int j : arr) {
//            cntArry[j - min]++;
//        }
//        //Update prefix sums
//        for (int i = 1; i < cntArry.length; i++) {
//            cntArry[i]+=cntArry[i-1];
//        }
//        //Calculate final output
//        for (int i = arr.length-1; i >=0; i--) {
//             output[cntArry[arr[i] - min] - 1] = arr[i];
//             cntArry[arr[i] - min]--;
//        }

        //Solution-2
//        int maxNum = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
//        int minNum = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);
//        int countRange = maxNum - minNum + 1;
//        int[] count = new int[countRange];
//        int[] output = new int[arr.length];
//
//        for (int num : arr) {
//            count[num - minNum]++;
//        }
//
//        for (int i = 1; i < countRange; i++) {
//            count[i] += count[i - 1];
//        }
//
//        for (int i = arr.length - 1; i >= 0; i--) {
//            output[count[arr[i] - minNum] - 1] = arr[i];
//            count[arr[i] - minNum]--;
//        }
//
//        return output;

        //Solution-3
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        int[] count = new int[max - min + 1];
        int[] output = new int[arr.length];
        for (int num : arr) {
            count[num - min]++;
        }

        int k = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                output[k++] = i+min;
            }
        }
        return output;
    }
}