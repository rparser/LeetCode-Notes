class Solution {
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : ops) {
            if (s.equals("+")) {
                int t = stack.pop();
                int score = t + stack.peek();
                stack.push(t);
                stack.push(score);
            } else if (s.equals("D"))
                stack.push(stack.peek() * 2);
            else if (s.equals("C"))
                stack.pop();
            else
                stack.push(Integer.parseInt(s));
        }
        int sum = 0;
        for (int i : stack)
            sum += i;
        return sum;
    }
}