package com.leetcode.solution;

import java.util.Iterator;

//284. 顶端迭代器
public class _284_PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> it;
    Integer next = null;

    public _284_PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
        if (it.hasNext()) {
            next = it.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        next = it.hasNext() ? it.next() : null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
