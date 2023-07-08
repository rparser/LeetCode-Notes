package com.leetcode.solution;

import java.util.*;

public class _341_Flatten_Nested_List_Iterator implements Iterator<Integer> {
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

    Deque<List<NestedInteger>> elementStack = new ArrayDeque<>();
    Deque<Integer> pointerStack = new ArrayDeque<>();
    List<NestedInteger> curList;
    //当前指针
    int pointer = 0;

    public void NestedIterator(List<NestedInteger> nestedList) {
        curList = nestedList;
    }

    @Override
    public Integer next() {
        // hasNext()后，指针一定是指在下一个Integer上的，所以直接取就好了
        if (hasNext())
            return curList.get(pointer++).getInteger();
        else
            return null;
    }

    @Override
    public boolean hasNext() {
        // next()会++pointer, 当pointer到curList尾部时，要跳出递归
        while (pointer == curList.size() && !elementStack.isEmpty()) {
            pointer = pointerStack.pop();
            curList = elementStack.pop();
        }
        // 遍历完最后一个curList里的元素，返回false
        if (pointer == curList.size())
            return false;

        // 判断是Integer还是List，如果是Integer
        if (curList.get(pointer).isInteger())
            return true;
            // 如果是List
        else {
            // 元素要加进整个list
            elementStack.push(curList);
            // pointer要指向下一个数字
            pointerStack.push(pointer + 1);
            curList = curList.get(pointer).getList();
            pointer = 0;
            return hasNext();
        }
    }

    private List<Integer> list;
    private int index;

    public void NestedIterator2(List<NestedInteger> nestedList) {
        list = integerIterator2(nestedList);
        index = -1;
    }

    // @Override
    public Integer next2() {
        if (this.hasNext2()) return list.get(++index);
        return null;
    }

    // @Override
    public boolean hasNext2() {
        return index + 1 < list.size();
    }

    private static List<Integer> integerIterator2(List<NestedInteger> nestedIntegerList) {
        ArrayList<Integer> list = new ArrayList<>(nestedIntegerList.size());
        for (NestedInteger tmp : nestedIntegerList) {
            if (tmp.isInteger())
                list.add(tmp.getInteger());
            else
                list.addAll(integerIterator2(tmp.getList()));
        }
        return list;
    }
}
