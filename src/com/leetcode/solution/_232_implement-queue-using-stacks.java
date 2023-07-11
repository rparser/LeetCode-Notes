import java.util.Stack;

public class MyQueue232 {
    private Stack<Integer> in = null;
    private Stack<Integer> out = null;

    /**
     * Initialize your data structure here.
     */
    public MyQueue232() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (out.isEmpty()) {
            int size = in.size();
            for (int i = 0; i < size; i++)
                out.push(in.pop());
        }
        if (out.isEmpty()) return -1;
        else return out.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (out.isEmpty()) {
            int size = in.size();
            for (int i = 0; i < size; i++)
                out.push(in.pop());
        }
        return out.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return in.empty() && out.empty();
    }
}