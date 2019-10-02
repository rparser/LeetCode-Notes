package com.leetcode.solution;

import java.util.*;

/**
 * 思路： use map1: map to store <c, set of char after c>
 * use map2: degree to store <c, # of char before c> //based on loop not the final one
 * Iterate each two adjacent string, to fill map and update degree
 * use a queue to do BFS. add c to queue when its degree is 0. When remove a c, minus degree
 * ! watch edge case,  loop case
 * Complexity: time: O(n) space: O(n)
 */

public class _269_AlienDictionary {
    public String alienOrder(String[] words) {
        HashMap<Character, Set<Character>> map = new HashMap<>();//<c, char after c>
        HashMap<Character, Integer> degree = new HashMap<>();//<c, # of char before c>
        StringBuilder res = new StringBuilder();
        //initialize degree map
        for (String s : words) {
            char[] word = s.toCharArray();
            for (char c : word) degree.put(c, 0);
        }
        //compare adjacent string & fill map
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int len = Math.min(cur.length(), next.length());
            for (int j = 0; j < len; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet<>();//watch 'Set' declaration
                    if (map.containsKey(c1)) set = map.get(c1);
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;//rest comparision is meaningless & not record it!
                } else
                    //edge case - no order: ["wrtkj","wrt"] 1:next stop at end 2: cur still have lefts
                    if (j + 1 == next.length() && j + 1 < cur.length())
                        return "";
            }
        }
        //BFS - use Queue to pop char in order
        Queue<Character> queue = new LinkedList<Character>();
        for (char c : degree.keySet())
            if (degree.get(c) == 0)
                queue.add(c);//eg:[zx,zy], c: z,x
        
        while (!queue.isEmpty()) {
            char cur = queue.remove();
            res.append(cur);
            if (map.containsKey(cur)) {
                for (char c : map.get(cur)) {
                    degree.put(c, degree.get(c) - 1);
                    if (degree.get(c) == 0) queue.add(c);//add next char
                }
            }
        }
        //avoid loops. only < possible -- eg: ["qd","ab"] res = qa
        if (res.length() != degree.size()) return "";
        return res.toString();
    }
}
