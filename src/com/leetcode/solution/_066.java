class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i]++;
            digits[i] = digits[i] % 10; //如果是10则变成0，否则保持原来的数
            if(digits[i] != 0) return digits; //如果不为0则表示不是9则计算完毕直接返回
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1; //只有全为9时才会在loop外返回
        return result;
    }
}