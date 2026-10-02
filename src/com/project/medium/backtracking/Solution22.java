package com.project.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Solution22 {

    public static void main(String[] args) {
        List<String> res = generateParenthesis(3);
        System.out.println(res);
    }

    //Solution-1
//    public static List<String> generateParenthesis(int n) {
//        List<String> res = new ArrayList<>();
//        backtrack(res, "", 0, 0, n);
//        return res;
//    }
//
//    public static void backtrack(List<String> res, String cur, int open, int close, int max) {
//        if (cur.length() == max*2) {
//            res.add(cur);
//            return;
//        }
//        if (open < max) {
//            backtrack(res, cur + "(", open + 1, close, max);
//        }
//        if (close < open) {
//            backtrack(res, cur + ")", open, close + 1, max);
//        }
//    }
    //Solution-2
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(res, sb, 0, 0, n);
        return res;
    }

    public static void backtrack(List<String> res, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max*2) {
            res.add(cur.toString());
            return;
        }
        if (open < max) {
            backtrack(res, cur.append("("), open + 1, close, max);
            cur.deleteCharAt(cur.length()-1);
        }
        if (close < open) {
            backtrack(res, cur.append(")"), open, close + 1, max);
            cur.deleteCharAt(cur.length()-1);
        }
    }
}
/*
Using a StringBuilder over String for manipulation has a huge impact on performance
 */