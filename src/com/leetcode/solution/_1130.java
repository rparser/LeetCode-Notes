class Solution {
    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> stack = new Stack();
        stack.push(Integer.MAX_VALUE);
        int ans = 0;
        for (int n : arr) {
            while (stack.peek() < n) ans += stack.pop() * Math.min(stack.peek(), n);
            stack.push(n);
        }
        while (stack.size() > 2) ans += stack.pop() * stack.peek();
        return ans;
    }
}
