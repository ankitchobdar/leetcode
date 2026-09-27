package com.project;

import java.util.*;
import java.util.stream.Collectors;

public class Solution49 {
    public static void main(String[] args) {
        System.out.println(groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    //Solution-1
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, List<List<String>>> map = new HashMap<>();
        for(String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            if (map.containsKey(arr.length)) {
                List<List<String>> list = map.get(arr.length);
                boolean added = false;
                for (int i = 0; i < list.size(); i++) {
                    List<String> list2 = list.get(i);
                    char[] arr2 = list2.get(0).toCharArray();
                    Arrays.sort(arr2);
                    if (Arrays.equals(arr, arr2)) {
                        list2.add(s);
                        added = true;
                        break;
                    }
                }
                if(!added) {
                    List<String> list3 = new ArrayList<>();
                    list3.add(s);
                    map.get(arr.length).add(list3);
                }
            } else {
                List<List<String>> list4 = new ArrayList<>();
                List<String> list5 = new ArrayList<>();
                list5.add(s);
                list4.add(list5);
                map.put(arr.length, new ArrayList<>(list4));
            }
        }
        return map.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    //Solution-2
    public static String getSignature(String s) {
        int[] arr = new int[26];
        for(char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++) {
            if(arr[i] > 0) {
                sb.append((char)(i + 'a')).append(arr[i]);
            }
        }
        return sb.toString();
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> groups = new HashMap<>();
        for(String s : strs) {
            groups.computeIfAbsent(getSignature(s), k -> new ArrayList<>()).add(s);
        }
        result.addAll(groups.values());
        return result;
    }
}
