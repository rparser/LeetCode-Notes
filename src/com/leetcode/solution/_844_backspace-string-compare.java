import java.util.*;

class Solution {
    public boolean backspaceCompare(String S, String T) {
        return toRes(S).equals(toRes(T));
    }

    private String toRes(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != '#')
                stack.push(c);
            else if (!stack.isEmpty())
                stack.pop();
        }
        return String.valueOf(stack);
    }
}
