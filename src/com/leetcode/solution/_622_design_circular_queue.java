package com.leetcode.solution;

//数组+size+front+rear
class _622_design_circular_queue {
    private int[] arr;
    private int size;
    private int front;
    // rear是插入的位置
    private int rear;

    //初始化,大小为k
    public _622_design_circular_queue(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Queue size is less than 0!");

        this.arr = new int[size];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // 插入，成功返回true
    public boolean enQueue(int value) {
        //已满
        if (size == arr.length) {
            return false;
        }
        //插入队尾
        arr[rear] = value;
        size++;
        //如果队尾是数组最后一位，变为0，否则++
        if (rear == arr.length - 1) {
            rear = 0;
        } else {
            rear++;
        }

        return true;
    }

    //删除队首的数值(poll)
    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        //队首位置提前
        if (front == arr.length - 1) {
            front = 0;
        } else {
            front++;
        }

        size--;
        return true;
    }

    //peek Front
    public int Front() {
        if (size == 0) {
            return -1;
        }

        return arr[front];
    }

    //peek Rear
    public int Rear() {
        if (size == 0) {
            return -1;
        }
        // 如果0从enQueue证明是数组最后一位，返回最后一位
        // 否则返回前一位, rear是直接插入的位置
        return rear == 0 ? arr[arr.length - 1] : arr[rear - 1];
    }

    // 查询空
    public boolean isEmpty() {
        return size == 0;
    }

    // 查询满
    public boolean isFull() {
        return size == arr.length;
    }
}