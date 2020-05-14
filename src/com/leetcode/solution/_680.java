class Solution {
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while (l<=r){
            if (s.charAt(l) == s.charAt(r)){ //如果一致则向后推
                l++;
                r--;
            }
            else return isPalindrome(s,l,r-1) || isPalindrome(s,l+1,r); //不一致则看删除哪个
        }
        return true;
    }

    private boolean isPalindrome(String str, int s, int t){
        while (s <= t){
            if (str.charAt(s) == str.charAt(t)){
                s++;
                t--;
            }
            else return false;
        }
        return true;
    }
}