class Solution {
    public int maxProfit(int[] prices) {
        int result = 0, minPrice =Integer.MAX_VALUE;
        for(int i : prices){
            // 小于则直接更新
            if(i < minPrice) minPrice = i;
            else {
                //大于则加入结果再更新
                result += (i - minPrice);
                minPrice = i;
            }
        }
        return result;
    }
}