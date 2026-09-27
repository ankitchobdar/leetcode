package com.project.medium;

import java.util.Arrays;

public class Solution6 {
    public static void main(String[] args) {
        System.out.println(convert3("PAYPALISHIRING", 3));
    }

    //Solution-1
    public static String convert(String s, int numRows) {
        if(s.length() == numRows || numRows == 1){
            return s;
        }
        char[][] arr = new char[numRows][s.length()];
        char[] alphabets = s.toCharArray();
        int index = 0, j = 0;

        for (int i = 0; i < numRows; i++) {
            arr[i][j] = alphabets[index++];
            if(index == s.length())
                break;
        }
        outerloop:
        while(index < s.length()) {
            for (int i = numRows - 2; i >= 0; i--) {
                arr[i][++j] = alphabets[index++];
                if (index == s.length())
                    break outerloop;
            }
            for (int i = 1; i < numRows; i++) {
                arr[i][j] = alphabets[index++];
                if (index == s.length())
                    break outerloop;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; i++){
            for (int k = 0; k < s.length(); k++) {
                if(arr[i][k] != 0)
                    sb.append(arr[i][k]);
            }
        }
        //System.out.println(Arrays.deepToString(arr));
        return sb.toString();
    }

    //Solution-2
    public static String convert2(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;

        int n = s.length();
        int cycle = 2 * (numRows - 1);
        char[] result = new char[n];
        int idx = 0;

        for (int row = 0; row < numRows; row++) {
            for (int j = row; j < n; j += cycle) {
                result[idx++] = s.charAt(j);
                int diag = j + cycle - 2 * row;
                if (row != 0 && row != numRows - 1 && diag < n) {
                    result[idx++] = s.charAt(diag);
                }
            }
        }
        return new String(result);
    }

    //Solution-3
    public static String convert3(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;

        String[] sbs = new String[numRows];
        Arrays.fill(sbs, "");
        int i = 0, j = 0;
        boolean rev = false;
        while(i != s.length()){
            if(!rev) {
                sbs[j++] += s.charAt(i++);
                if(j == numRows) {
                    rev = true;
                    j--;
                }
            } else {
                sbs[--j] += s.charAt(i++);
                if (j == 0) {
                    rev = false;
                    j++;
                }
            }
        }
        //System.out.println(Arrays.deepToString(sbs));
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < numRows; k++){
            sb.append(sbs[k]);
        }
        return sb.toString();
    }
}
