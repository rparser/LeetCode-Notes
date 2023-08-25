package com.leetcode.solution;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 最常用K点
 * map(num:count)，按照count加入pq,只保留k-size，
 */

public class _347_Top_K_Frequent_Elements {
    public int[] topKFrequentTongPaiXuON(int[] nums, int k) {
        // 统计每个数字出现的次数
        Map<Integer, Integer> counterMap = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        // 一个数字最多出现 nums.length 次，因此定义一个长度为 nums.length + 1 的数组，freqList[i] 中存储出现次数为 i 的所有数字。
        List<Integer>[] freqList = new List[nums.length + 1];
        for (int i = 0; i < freqList.length; i++) {
            freqList[i] = new ArrayList<>();
        }
        counterMap.forEach((num, freq) -> {
            freqList[freq].add(num);
        });
        // 按照出现频次，从大到小遍历频次数组，构造返回结果。
        int[] res = new int[k];
        int idx = 0;
        for (int freq = freqList.length - 1; freq > 0; freq--) {
            for (int num : freqList[freq]) {
                res[idx++] = num;
                if (idx == k) {
                    return res;
                }
            }
        }
        return res;
    }


    public List<Integer> topKFrequentON(int[] nums, int k) {
        //<数字，这个数字出现的次数>
        Map<Integer, Integer> map = new HashMap<>();
        //建立一个有num.length+1个List的数组bucket
        ArrayList<Integer>[] bucket = new ArrayList[nums.length + 1];
        List<Integer> res = new ArrayList<>();
        //统计每个num出现的个数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }//统计个数

        for (int key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            //此时，bucket数组里，每个点0-n，都记录了这个数字出现多少次，比如bucket[fr]代表出现fr次的数字有哪些
            bucket[frequency].add(key);
        }
        // 从高频到低频
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {  //如果这个值有值，则依次加入结果集合
                for (int i = 0; i < bucket[pos].size() && res.size() < k; i++) {
                    res.add(bucket[pos].get(i));
                }
            }
        }
        return res;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        } // num : count

        PriorityQueue<Integer> queue =
                new PriorityQueue<>(Comparator.comparingInt(count::get));

        // keep k top frequent elements in the queue
        for (int n : count.keySet()) {
            queue.add(n);
            if (queue.size() > k) {
                queue.poll();
            } //只保留k-size的queue
        }

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!queue.isEmpty()) {
            top_k.add(queue.poll());
        }
        Collections.reverse(top_k);
        return top_k;
    }
}
