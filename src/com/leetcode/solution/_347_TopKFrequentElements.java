package com.leetcode.solution;

import java.util.*;

/**
 * 最常用K点
 * map(num:count)，按照count加入pq,只保留k-size，
 */

public class _347_TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n : nums) count.put(n, count.getOrDefault(n, 0) + 1); // num : count

        PriorityQueue<Integer> queue =
                new PriorityQueue<>(Comparator.comparingInt(count::get));

        // keep k top frequent elements in the queue
        for (int n : count.keySet()) {
            queue.add(n);
            if (queue.size() > k) queue.poll(); //只保留k-size的queue
        }

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!queue.isEmpty()) top_k.add(queue.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    public List<Integer> topKFrequentON(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }
}
