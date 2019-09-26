package com.leetcode.solution;

import java.util.*;

/**
 * Space: O(N)
 * 思路：Use HashMap + ArrayList to build the data structure.
 * Map store pairs of <val, index in list> and List is store the val existed.
 * They are same size. Add and remove together
 * When removal, list: replace the removed element with last added element.
 * map: replace the index(val) of last added element, and remove val(key)
 */

public class _380_InsertDeleteGetRandomO1 {
    Map<Integer, Integer> map;//<val, index in arrlist>
    List<Integer> list;//keep a record of value for get random
    int size;//map and list are the same size
    Random rand;

    /**
     * Initialize your data structure here.
     */
    public _380_InsertDeleteGetRandomO1() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.size = 0;
        this.rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, size);
        list.add(val);
        size++;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int last = list.get(size - 1);
            list.set(map.get(val), last);//use last added ele to fill the element to be removed
            map.put(last, map.get(val));//update last added ele's index in list
            map.remove(val);
            list.remove(size - 1);//remove at end is constant
            size--;
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rand.nextInt(size));
    }
}
