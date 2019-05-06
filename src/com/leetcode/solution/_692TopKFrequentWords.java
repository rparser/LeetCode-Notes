package com.leetcode.solution;

import java.util.*;

/**
 * K最常见单词
 * <p>
 * Time Complexity: O(NlogN), where NN is the length of words.
 * We count the frequency of each word in O(N) time, then we sort the given words in O(NlogN) time.
 * Space Complexity: O(N), the space used to store our candidates.
 */

public class _692TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList<>(count.keySet());
        candidates.sort((w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }

    /**
     * Heap解法
     * Time Complexity: O(Nlogk), where N is the length of words.
     * We count the frequency of each word in O(N) time, then we add NN words to the heap, each in O(logk) time.
     * Finally, we pop from the heap up to kk times. As k≤N, this is O(Nlogk) in total.
     * Space Complexity: O(N), the space used to store our count.
     */

    public List<String> topKFrequentHeap(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                        w2.compareTo(w1) : count.get(w1) - count.get(w2));

        for (String word : count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList<>();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}
