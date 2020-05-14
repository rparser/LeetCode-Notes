class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length < 1) return 0;
        int result = 0;
        int max = 0;
        int prev = Integer.MIN_VALUE;
        for(int i : nums){
            if(i > prev){
                prev = i;
                result++;
                max = Math.max(result, max);
            }else{
                prev = i;
                result = 1;
            }
        }
        return max;
    }
}