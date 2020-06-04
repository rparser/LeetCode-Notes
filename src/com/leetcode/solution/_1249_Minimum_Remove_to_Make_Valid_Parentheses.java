class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') stack.add(i); //(则加入stack的位置
            if (sb.charAt(i) == ')') { //)如果里面已有（则弹出最近的（，否则标记
                if (!stack.empty()) stack.pop();
                else sb.setCharAt(i, '*');
            }
        }
        while (!stack.empty())
            sb.setCharAt(stack.pop(), '*'); //如果还有剩余的则标记位置
        return sb.toString().replaceAll("\\*", "");
    }
}