class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>(); // to store last value and sign before any parentheses
        int result = 0; // current result within one priority (in the same parentheses)
        int sign = 1; //当前正负
        int cur = 0; //当前数值
        int flag = 1;
        String[] strs = s.split(" ");

        for (String str : strs) {
            switch (str) {
                case "add": //如果是符号，则要计算后再push入
                    sign = 1;
                    flag = 1;
                    // stack.push(stack.pop() + stack.pop());
                    break;

                case "sub":
                    sign = -1;
                    flag = -1;
                    // stack.push(-stack.pop() + stack.pop());
                    break;

                case "(":
                    stack.push(result);
                    stack.push(sign);
                    result = 0; //reset result
                    sign = 1; //reset sign
                    break;

                case ")":
                    result += sign * cur; //当前括号内的result
                    cur = result;
                    sign = stack.pop();
                    result = stack.pop();
                    break;

                default:
                    cur += flag * sign * Integer.parseInt(str);
                    flag = 1;
            }
        }
        // result += sign * cur; // last sign and number
        result += cur;
        return result;
    }
}