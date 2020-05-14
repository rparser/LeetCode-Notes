class Solution {
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    private boolean check(String s, int start, int count) {
        if (count < 0) return false;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            else if (c == ')') {
                if (count-- == 0) return false;
            } else if (c == '*') {
                return check(s, i + 1, count + 1) ||  // 作为 (
                        check(s, i + 1, count - 1) || // 作为 )，抵消一个左括号
                        check(s, i + 1, count);       // 作为 空
            }
        }
        return count == 0;
    }

    //stack
    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>(), star = new Stack<>(); // index stack
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') left.push(i);
            else if (c == '*') star.push(i);
            else {
                if (!left.isEmpty()) left.pop();
                else if (!star.isEmpty()) star.pop();
                else return false;
            }
        }

        while (!left.isEmpty() && !star.isEmpty()) {
            if (left.pop() > star.pop()) return false;
        }
        return left.isEmpty();
    }
}