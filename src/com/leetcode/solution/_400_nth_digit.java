package com.leetcode.solution;

public class _400_nth_digit {
    // O(N) ~O(logN?), O(1) ~O(logN)
    // 序列从1开始并非0
    public int findNthDigit(int n) {
        // digits是该数字有几位，count是有几个
        // 例如1,9; 2,90; 3,900三位数有900个
        int digits = 1;
        long count = 9;
        while (n - count * digits > 0) {
            n -= count * digits;
            count *= 10;
            digits++;
        }
        // numberIndex是第几个数字，如第3个数字1002
        // digitsIndex是数字的第几位，如第三位1234的3
        int numberIndex = n / digits;
        int digitsIndex = n % digits;// 注意由于上面的计算，n现在表示digits位数的第n个数字
        // 这步重要，比如11个数字，剩余2，这个2%2=0代表10的第二位数字，而不是第0位
        if (digitsIndex == 0)
            digitsIndex = digits;
        //找到该数字的base (4位数的1000)
        long sum = 1;
        for (int k = 1; k < digits; k++)
            sum *= 10;
        //base加上对应的数，如上digitsIndex == digits是代表前一个数字的最后一位数，所以需要-1
        //比如11 ~2 %2=0代表第0个数的第二位数字，并非第1个数(11)
        sum += (digitsIndex == digits) ? (numberIndex - 1) : (numberIndex);
        //先除digits - digitsIndex - 1个
        for (int i = 0; i < digits - digitsIndex; i++)
            sum /= 10;
        //最后一个取余
        return (int) sum % 10;
    }
}
