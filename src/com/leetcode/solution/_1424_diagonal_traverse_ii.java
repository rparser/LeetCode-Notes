package com.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class _1424_diagonal_traverse_ii {
    //显然每条对角线的值下标值相同。
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        //一共多少个数字
        int total = 0;
        //根据对角线i+j唯一且相同，LinkedHashMap保持插入排序。效率是最优的
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                List<Integer> indexList = map.getOrDefault(i + j, new ArrayList<>());
                //每个list倒序输入，比如<2,4>实际顺序从左下到右上是<4,2>,一会儿倒序输出
                indexList.add(nums.get(i).get(j));
                map.putIfAbsent(i + j, indexList);
                total++;
            }
        }
        //结果是一维数组
        int[] result = new int[total];
        int index = 0;
        //遍历map，得到结果。
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int j = list.size() - 1; j >= 0; j--) {
                result[index] = list.get(j);
                index++;
            }
        }
        return result;
    }
}
