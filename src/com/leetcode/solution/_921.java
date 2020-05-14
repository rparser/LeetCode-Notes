class Solution {
    public int minAddToMakeValid(String S) {
        int result = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : S.toCharArray()){
            if(c == '(') stack.push(c);
            else{
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else result++;
            }
        }
        return result + stack.size();
    }
}