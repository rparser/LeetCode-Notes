package com.leetcode.solution;

class _066_plus_one {
    // O(N), O(N)
    public int[] plusOne(int[] digits) {
        //从最后一位开始
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            //如果是10则变成0，否则保持原来的数
            digits[i] = digits[i] % 10;
            //如果不为0则表示不是9则计算完毕直接返回
            if (digits[i] != 0)
                return digits;
        }
        // 如果上面没返回，证明数组为99999....
        int[] result = new int[digits.length + 1];
        result[0] = 1; //只有全为9时才会在loop外返回
        return result;
    }
}