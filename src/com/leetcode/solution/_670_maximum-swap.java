//  O(n), O(1)
//我们将计算 last[d] = i，最后一次出现的数字 d（如果存在）的索引 \text ii。
//然后，从左到右扫描数字时，如果将来有较大的数字，我们将用最大的数字交换；如果有多个这样的数字，我们将用最开始遇到的数字交换。
class Solution {
    public int maximumSwap(int num) {
        char[] charsArray = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < charsArray.length; i++)
            last[charsArray[i] - '0'] = i;

        for (int i = 0; i < charsArray.length; i++)
            for (int d = 9; d > charsArray[i] - '0'; d--)
                if (last[d] > i) {
                    char tmp = charsArray[i];
                    charsArray[i] = charsArray[last[d]];
                    charsArray[last[d]] = tmp;
                    return Integer.parseInt(new String(charsArray));
                }
        return num;
    }
}