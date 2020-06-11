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
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer>[] bucket = new ArrayList[nums.length + 1]; //建立一个有num.length+1个List的数组bucket
        List<Integer> res = new ArrayList<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1); //统计个数

        for (int key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null)
                bucket[frequency] = new ArrayList<>();
            //此时，bucket数组里，每个点0-n，都记录了这个数字出现多少次，比如bucket[fr]代表出现fr次的数字有哪些
            bucket[frequency].add(key);
        }

        for (int pos = bucket.length - 1; pos >= 0; pos--) { //从大到小
            if (bucket[pos] != null) { //如果这个值有值，则依次加入结果集合
                for (int i = 0; i < bucket[pos].size() && res.size() < k; i++)
                    res.add(bucket[pos].get(i));
            }
        }
        return res;
    }
}
