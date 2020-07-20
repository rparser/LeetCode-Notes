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

class _269_Alien_Dictionary {
    // O(N), O(N)
    public String alienOrder(String[] words) {
        HashMap<Character, Set<Character>> graph = new HashMap<>();
        StringBuilder result = new StringBuilder();
        // 分别和后一个比较单词，最多比较n-1次
        for (int i = 0; i < words.length - 1; i++) {
            // 这里改成这样就是为了防止 abc > ab
            int len = Math.max(words[i].length(), words[i + 1].length());

            for (int j = 0; j < len; j++) {
                // 这里是个坑 要防止 abc -> ab 这种情况
                if (j >= words[i].length())
                    break;
                if (j >= words[i + 1].length())
                    return "";
                // 同样位置 单词相等则继续
                if (words[i].charAt(j) == words[i + 1].charAt(j))
                    continue;
                // 单词不等，则第一个不等的字母，放到<前面的c,排在前面的c 的后面的c set里>
                Set<Character> set = graph.computeIfAbsent(words[i].charAt(j), k -> new HashSet<>());
                set.add(words[i + 1].charAt(j));
                graph.put(words[i].charAt(j), set);
                break;
            }
        }

        //Topological Sorting
        // 入度初始化, -1表示不存在
        int[] inDegree = new int[26];
        Arrays.fill(inDegree, -1);

        // 把所有已有的字母入度设成0，没有的还是-1
        for (String word : words)
            for (char c : word.toCharArray())
                inDegree[c - 'a'] = 0;


        // 入度in-degree 为0的先学习 出现在别的字母的set里的++ 想成一个课程表
        for (Character key : graph.keySet())
            for (Character value : graph.get(key))
                inDegree[value - 'a']++;

        LinkedList<Character> queue = new LinkedList<>();
        //需要排序的字母
        int numChar = 0;
        for (int i = 0; i < 26; i++) {
            //in-degree 为0的，可以任意排列直接加入
            if (inDegree[i] == 0)
                queue.add((char) (i + 'a'));
            // 统计所有出现过的字母数
            if (inDegree[i] != -1)
                numChar++;
        }

        while (!queue.isEmpty()) {
            Character firstChar = queue.poll();
            result.append(firstChar);
            if (graph.containsKey(firstChar))
                for (Character nextChar : graph.get(firstChar)) {
                    // 找到一个set里的字母则入度-1
                    inDegree[nextChar - 'a']--;
                    // 把入度为0的字母加入result
                    if (inDegree[nextChar - 'a'] == 0)
                        queue.add(nextChar);

                }
        }
        // 比如有孤单字母"a","b","ad",此时结果是有的（d），但不能输出因为长度不对（不是3）
        if (result.length() != numChar)
            return "";

        return result.toString();
    }
}
