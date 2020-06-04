class Solution {
    public double myPow(double x, int n) {
        boolean flag = n >= 0 ? true : false;
        double result = 1.0;
        while (n != 0) {
            // n&1是看最后一位是否为1，例如10是1010不为1
            if ((n & 1) != 0) {
                //如果是1加入结果，否则跳过直接自身平方
                result *= x;
            }
            x = x * x;
            n = n / 2;
        }
        return flag ? result : 1 / result;
    }
}