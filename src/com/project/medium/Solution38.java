package com.project.medium;

public class Solution38 {
    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    //Solution-1
    public static String countAndSay(int n) {
        StringBuilder sb = new StringBuilder("1");

        if (n-- == 1) return "1";

        while(n > 0) {
            int count = 0;
            char current = '0';
            String str = sb.toString();
            sb.setLength(0);
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) != current && count != 0) {
                    sb.append(String.valueOf(count)).append(current);
                    count = 0;
                }
                current = str.charAt(i);
                count++;
            }
            sb = new StringBuilder(sb+String.valueOf(count) + current);
            n--;
        }

        return sb.toString();
    }

    //Solution-2
    public static String countAndSay1(int n) {
        String res = "1";

        for (int i = 1; i < n; i++) {
            res = buildNext(res);
        }
        return res;
    }

    public static String buildNext(String s) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                result.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        result.append(count).append(s.charAt(s.length() - 1));
        return result.toString();
    }
}
