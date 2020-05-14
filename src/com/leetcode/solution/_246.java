class Solution {
    public boolean isStrobogrammatic(String num) {
        //建立map存储可能
        Map<Character, Character> map = Map.of('6','9','9','6','0','0','8','8','1','1');
        char[] digits = num.toCharArray();
        for(int i = 0; i < (num.length()+1) /2 ; i++){
            char a = digits[i];
            char b = digits[num.length() - i - 1];
            if(!map.containsKey(a) || map.get(a) != b) return false;
        }
        return true;
    }
}