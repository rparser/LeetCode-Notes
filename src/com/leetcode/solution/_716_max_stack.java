package com.leetcode.solution;

import java.util.*;
//时间复杂度：O(logn)。top() 操作的时间复杂度为 O(1)，其余操作的时间复杂度为 O(logn)，因此总的时间复杂度为 O(logn)。
//空间复杂度：O(n)。

public class _716_max_stack {
    // 非常需要BinarySearch得到小于target的位置,需要自定义BinarySearch(ArrayList<Integer> list, int num, int lo, int hi)
    // 加入的顺序
    LinkedList<Integer> linkedStacks = new LinkedList<>();
    // 排序的List
    ArrayList<Integer> maxStacks = new ArrayList<>();
    // push(x) -- 将元素 x 压入栈中。
    public void push(int x) {
        linkedStacks.add(x);
        if (maxStacks.size() == 0)
            maxStacks.add(x);
        else {
            // 如果大于最后一个加到队尾
            if (x >= maxStacks.get(maxStacks.size() - 1))
                maxStacks.add(x);
            // 如果小于第一个加到队头
            else if (x < maxStacks.get(0))
                maxStacks.add(0, x);
            //否则加到相应的位置
            else {
                int maxIndex = binarySearch(maxStacks, x, 0, maxStacks.size());
                maxStacks.add(maxIndex, x);
            }
        }
    }

    // pop() -- 移除栈顶元素并返回这个值。
    public int pop() {
        if (maxStacks.get(maxStacks.size() - 1).equals(linkedStacks.get(linkedStacks.size() - 1)))
            maxStacks.remove(maxStacks.size() - 1);
        else {
            int t = binarySearch(maxStacks, linkedStacks.get(linkedStacks.size() - 1), 0, maxStacks.size());
            maxStacks.remove(t - 1);
        }
        return linkedStacks.remove(linkedStacks.size() - 1);
    }
    //top() -- 返回栈顶元素。
    public int top() {
        return linkedStacks.get(linkedStacks.size() - 1);
    }
    // peekMax() -- 返回栈中最大元素。
    public int peekMax() {
        return maxStacks.get(maxStacks.size() - 1);
    }
    // popMax() -- 返回栈中最大的元素，并将其删除。如果有多个最大元素，只要删除最靠近栈顶的那个。
    public int popMax() {
        for (int i = linkedStacks.size() - 1; i >= 0; --i) {
            if (linkedStacks.get(i).equals(maxStacks.get(maxStacks.size() - 1))) {
                linkedStacks.remove(i);
                break;
            }
        }
        return maxStacks.remove(maxStacks.size() - 1);
    }

    public int binarySearch(ArrayList<Integer> list, int num, int lo, int hi) {
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (list.get(mid) <= num) {
            int i = 1;
            while (mid + i < list.size() && list.get(mid + i) <= num)
                i++;

            if (mid + i < list.size() && list.get(mid + i) > num)
                return mid + i;
            else
                return binarySearch(list, num, mid + 1, hi);
        } else
            return binarySearch(list, num, lo, mid - 1);
    }
}
