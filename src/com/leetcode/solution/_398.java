//假设当前正要读取第n个数据，则我们以1/n的概率留下该数据，否则留下前n-1个数据中的一个。

class Solution {
    private int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Random r = new Random();
        int n = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == target) {
                //我们的目标对象中选取。
                n++;
                //我们以1/n的概率留下该数据
                if (r.nextInt() % n == 0) index = i;
            }
        return index;
    }
}