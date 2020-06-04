//头尾都设成最长长度 O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        //用一个 HashMap ，存储以当前 key 为边界的连续序列的长度。可以再结合代码理解一下
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            //已经考虑过的数字就跳过，必须跳过，不然会出错
            //比如 [1 2 1]
            //最后的 1 如果不跳过，因为之前的 2 的最长长度已经更新成 2 了，所以会出错
            if (map.containsKey(num)) {
                continue;
            }
            //找到以左边数字结尾的最长序列，默认为 0
            int left = map.getOrDefault(num - 1, 0);
            //找到以右边数开头的最长序列，默认为 0
            int right = map.getOrDefault(num + 1, 0);
            int sum = left + 1 + right;
            max = Math.max(max, sum);

            //将当前数字放到 map 中，防止重复考虑数字，value 可以随便给一个值
            map.put(num, -1);
            //更新左边界长度
            map.put(num - left, sum);
            //更新右边界长度
            map.put(num + right, sum);
        }
        return max;
    }
}