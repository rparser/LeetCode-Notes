class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        for (int i = 0; i < 2 * nums.length - 1; i++) {
            int index = i % nums.length; // 取模，实现循环数组
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) { // 找到下一个更大元素
                res[stack.pop()] = nums[index]; // 栈中保存的是索引
            }
            stack.push(index);
        }
        return res;
    }
}
