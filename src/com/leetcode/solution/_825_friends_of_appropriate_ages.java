package com.leetcode.solution;

class _825_friends_of_appropriate_ages {
    //O(A^2 + N)其中N 是人数，A 是年龄的种数,O(A)，count 的空间开销。
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        // 统计每个年龄的人的个数
        for (int age : ages)
            count[age]++;

        int result = 0;
        for (int i = 0; i <= 120; i++) {
            if (count[i] == 0)
                continue;
            // AB同龄
            // 对于第1个条件，可以发送的情况是：i > 0.5 * i + 7
            // count[i] - 1 因为不能发给自己
            if (i > 14)
                result += count[i] * (count[i] - 1);
            // 不同年龄，B必须同时满足
            // 1. ageB > 0.5 * ageA + 7
            // 2. 要小于A（等于的情况计算过了）
            // 3. 可以划入2
            // 这里在循环条件中控制了1，内层判断2.对于满足条件的，每个i可以给所有j发，所以是count[i] * count[j]
            for (int j = 0; j < i; j++)
                if (j > 0.5 * i + 7)
                    result += count[i] * count[j];
        }
        return result;
    }
}
