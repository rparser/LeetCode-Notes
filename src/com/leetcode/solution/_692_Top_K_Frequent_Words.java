package com.leetcode.solution;

import java.util.*;

public class _692_Top_K_Frequent_Words {
    /**
     * Heap解法
     * Time Complexity: O(Nlogk), where N is the length of words.
     * We count the frequency of each word in O(N) time, then we add NN words to the heap, each in O(logk) time.
     * Finally, we pop from the heap up to kk times. As k≤N, this is O(Nlogk) in total.
     * Space Complexity: O(N), the space used to store our count.
     */
    //O(Nlogk), O(N)
    public List<String> topKFrequentHeap(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words)
            count.put(word, count.getOrDefault(word, 0) + 1); //统计频率
        //如果相等则比较字母顺序
        PriorityQueue<String> pq = new PriorityQueue<>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                        w2.compareTo(w1) : count.get(w1) - count.get(w2));
        //保持K个
        for (String word : count.keySet()) {
            pq.offer(word);
            if (pq.size() > k)
                pq.poll();
        }
        // 加入结果集
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty())
            result.add(pq.poll());

        Collections.reverse(result);
        return result;
    }
}
