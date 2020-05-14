class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int ls = s.length(), lt = t.length();
        if(Math.abs(ls - lt) > 1) return false;
        if(ls > lt) return isOneEditDistance(t, s);
        for(int i = 0; i < ls; i++)
            if(s.charAt(i) != t.charAt(i)){
                if(ls != lt) return s.substring(i).equals(t.substring(i + 1));
                else return i == ls -1 || s.substring(i+1).equals(t.substring(i + 1));
            }
        return !s.equals(t);
    }
}