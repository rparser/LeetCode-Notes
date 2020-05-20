class ArrayStack {
    private int[] arrInt;//内置数组
    private int top;//栈顶指针

    public ArrayStack(int size) {
        this.arrInt = new int[size];
        top = -1;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return -1 == top;
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return arrInt.length - 1 == top;
    }

    /**
     * 向栈顶插入一个元素
     *
     * @param item
     */
    public void push(int item) {
        if (isFull()) {
            throw new RuntimeException("栈已满");
        }
        arrInt[++top] = item;
    }

    /**
     * 从栈顶取出一个元素
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return arrInt[top--];
    }
}