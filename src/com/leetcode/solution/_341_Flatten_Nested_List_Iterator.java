package com.leetcode.solution;

import java.util.*;

public class _341_FlattenNestedListIterator implements Iterator<Integer> {
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    List<Integer> list = new ArrayList<>();
    int pos = 0;//current position

    public _341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        traverse(nestedList); //use arrayList to store nestedList
    }

    public void traverse(List<NestedInteger> nestedList) {
        if (nestedList == null) return;
        for (NestedInteger e : nestedList) {
            if (e.isInteger())
                list.add(e.getInteger());
            else
                traverse(e.getList()); //do recursion when meeting list element
        }
    }

    @Override
    public Integer next() {
        return list.get(pos++);
    }

    @Override
    public boolean hasNext() {
        return pos < list.size();
    }
}
