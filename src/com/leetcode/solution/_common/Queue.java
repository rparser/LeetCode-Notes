package com.leetcode.solution._common;

public class Queue<T> {
    private static final int DEFAULT_SIZE = 10;
    private T elementData[];
    private int front, rear;
    private int size;

    public Queue() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
        front = rear = 0;
    }

    public Queue(int capacity) {
        elementData = (T[]) new Object[capacity];
        front = rear = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * data 入队,添加成功返回true,否则返回false,可扩容
     *
     * @param data
     * @return
     */
    public boolean add(T data) {
        //判断是否满队
        if (this.front == (this.rear + 1) % this.elementData.length) {
            ensureCapacity(elementData.length * 2 + 1);
        }
        //添加data
        elementData[this.rear] = data;
        //更新rear指向下一个空元素的位置
        this.rear = (this.rear + 1) % elementData.length;
        size++;
        return true;
    }

    /**
     * 返回队头元素,不执行删除操作,若队列为空,返回null
     *
     * @return
     */
    public T peek() {
        return elementData[front];
    }

    /**
     * 出队,执行删除操作,返回队头元素,若队列为空,返回null
     *
     * @return
     */
    public T poll() {
        T temp = this.elementData[this.front];
        this.front = (this.front + 1) % this.elementData.length;
        size--;
        return temp;
    }

    public void clearQueue() {
        for (int i = this.front; i != this.rear; i = (i + 1) % elementData.length) {
            elementData[i] = null;
        }
        //复位
        this.front = this.rear = 0;
        size = 0;
    }

    /**
     * 扩容的方法
     *
     * @param capacity
     */
    public void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity < size)
            return;

        T[] old = elementData;
        elementData = (T[]) new Object[capacity];
        int j = 0;
        //复制元素
        for (int i = this.front; i != this.rear; i = (i + 1) % old.length) {
            elementData[j++] = old[i];
        }
        //恢复front,rear指向
        this.front = 0;
        this.rear = j;
    }
}
