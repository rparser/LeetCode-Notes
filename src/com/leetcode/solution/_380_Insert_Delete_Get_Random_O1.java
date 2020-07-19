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

public class _380_Insert_Delete_Get_Random_O1 {
    // <val, index in arrlist>
    Map<Integer, Integer> map;
    List<Integer> list;//keep a record of value for get random
    int size;//map and list are the same size
    Random rand;

    /**
     * Initialize your data structure here.
     */
    public _380_Insert_Delete_Get_Random_O1() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.size = 0;
        this.rand = new Random();
    }

    // 插入分别加入map<val,size> 和 list, size++
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;

        map.put(val, size);
        list.add(val);
        size++;
        return true;
    }

    // 删除：把最新加的数（list最后一位），覆盖set要删除的数，然后删除list最后的数
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
