class Solution {
    public boolean backspaceCompare(String S, String T) {
        return toRes(S).equals(toRes(T));
    }

    private String toRes(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c != '#')
                stack.push(c);
            else if (!stack.isEmpty())
                stack.pop();
        }
        return String.valueOf(stack);
    }
}
