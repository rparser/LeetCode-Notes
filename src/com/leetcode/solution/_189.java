class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int count = 0;         // 记录交换位置的次数，n个同学一共需要换n次
        for (int start = 0; count < len; start++) {
            int cur = start;       // 从0位置开始换位子
            int pre = nums[cur];
            do {
                int next = (cur + k) % len;
                int temp = nums[next];    // 来到角落...
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            } while (start != cur);     // 循环暂停，回到起始位置，角落无人

        }
    }

    public static void rotate(int[] nums, int k) {
        int step = k % nums.length;  //计算步长
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put((i + step) % nums.length, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(i)) {
                nums[i] = hashMap.get(i);
            }

        }
    }
}
