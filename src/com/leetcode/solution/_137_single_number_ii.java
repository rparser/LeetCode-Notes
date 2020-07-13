package com.leetcode.solution;
//如果所有数字都出现了 3 次，那么每一列的 1 的个数就一定是 3 的倍数。
// 之所以有的列不是 3 的倍数，就是因为只出现了 1 次的数贡献出了 1。
// 所以所有不是 3 的倍数的列写 1，其他列写 0 ，就找到了这个出现 1 次的数。

class _137_single_number_ii {
    // O(N), O(1)
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            //考虑每一个数
            for (int num : nums)
                //当前位是否是 1,Unsigned right shift i位如果是1 count++
                //也可以>> right shift
                if ((num >> i & 1) == 1)
                    count++;
            //1 的个数是否是 3 的倍数，不是的话left shift i位添加为1
            if (count % 3 != 0)
                ans = ans | 1 << i;
        }
        return ans;
    }
}