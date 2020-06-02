//方法一：双指针
//        思路：
//        (1) 定义指针 i 按顺序遍历元素在去重后数组中对应的位置。
//        (2) 定义指针 j 从第二个元素开始遍历，和 i 重复就继续遍历，否则赋值给 i 。
//        (3) 直到指针 j 遍历完原数组，指针 i 指向的都是不重复元素。

class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        // 数组只有 0 个或者 1 个元素时直接返回数组长度。
        if (len < 2) return len;
        // 定义指针 i 指向去重后数组对应索引。
        int i = 0;
        // 从第二个元素开始遍历，判断是否和 i 指向元素重复。
        for (int j = 1; j < len; j++) {
            // 如果重复则继续遍历，否则去重。
            if (nums[j] != nums[i]) {
                // 找到了不一样的则将遍历到的不重复元素对应到去重后位置。
                i++;
                nums[i] = nums[j];
            }
        }
        // 最后去重后数组的长度为去重后最后一个元素的索引 i + 1 。
        return i + 1;
    }
}