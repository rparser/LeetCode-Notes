class Solution {

    private int[] arr;
    private int size;
    private int front;
    private int rear;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Queue size is less than 0!");
        }
        this.arr = new int[k];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (size == arr.length) {
            return false;
        }
        arr[rear] = value;
        size ++;
        rear = rear == arr.length - 1 ? 0 : rear + 1;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        front = front == arr.length - 1 ? 0 : front + 1;
        size--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (size == 0) {
            return -1;
        }
        return arr[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return rear == 0 ? arr[arr.length - 1] : arr[rear - 1];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == arr.length;
    }

}