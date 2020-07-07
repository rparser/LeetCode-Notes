package com.leetcode.solution;

import java.util.*;

/**
 * 1.两个overload的方法，引用一个参数的方法
 * 2.一个参数的方法返回两个参数的方法，设置为第一层，并不断递归
 */

public class _339_Nested_List_Weight_Sum {
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);  // 从第一层开始
    }

    public int depthSum(List<NestedInteger> list, int depth) {
        int sum = 0; // 记录结果
        for (NestedInteger n : list) {
            if (n.isInteger())  // 如果只有一个数
                sum += n.getInteger() * depth;
            else
                sum += depthSum(n.getList(), depth + 1);
        }
        return sum;
    }

    public interface NestedInteger {
        // Constructor initializes an empty nested list.
//        public NestedInteger();

        // Constructor initializes a single integer.
//        public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
