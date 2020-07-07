package com.leetcode.solution;

import java.util.*;

public class _381_insert_delete_getrandom_o1_duplicates_allowed {
    private List<Integer> vals;
    private Map<Integer, Set<Integer>> indexes;
    private int index;

    /**
     * Initialize your data structure here.
     */
    public _381_insert_delete_getrandom_o1_duplicates_allowed() {
        vals = new ArrayList<>();
        indexes = new HashMap<>();
        index = 0;
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        vals.add(val);
        //插入下标
        if (!indexes.containsKey(val))
            indexes.put(val, new HashSet<>());

        indexes.get(val).add(index);
        index++;
        return indexes.get(val).size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        //找到最后一个删除的下标
        if (!indexes.containsKey(val) || indexes.get(val).size() == 0)
            return false;
        Set<Integer> is = indexes.get(val);
        //得到一个下标
        int randomIndex = is.iterator().next();
        is.remove(randomIndex);
        //用最后一个值覆盖他
        int last = vals.get(index - 1);
        vals.set(randomIndex, last);
        //如果要删除的下标不等于最后一个元素下标，需要修改最后元素的下标映射
        if (randomIndex != index - 1) {
            //修改最后元素的下标映射
            Set<Integer> set = indexes.get(last);
            set.remove(index - 1);
            set.add(randomIndex);
        }
        //删除最后一个元素
        vals.remove(--index);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        if (index <= 0) throw new RuntimeException("null");
        int i = (int) Math.floor(Math.random() * index);
        return vals.get(i);
    }
}
