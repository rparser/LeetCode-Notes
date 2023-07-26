package com.leetcode.doordash;


/**
 * 解题思路
 * 根据题目描述，我们可以知道要将arr数组分成3份，并且对于这3份由0和1组成的二进制值要相同。因为是二进制，由0和1组成，
 * 所以，如果二进制相同的话，那么这3个分组中，1的个数一定是相同的。所以，以下图为例，我们统计出了一共有3个“1”，满足被3整除。所以，第一个条件就满足了。
 * <p>
 * “1”我们校验完毕之后，我们再来看看“0”。对于二进制来说，影响最终结果的其实是**“1”后面的“0”的个数**，
 * （即：00010和00000010是相等的，但是0010和001000就是不等的了）。那么我们可以推导出在arr数组中最后的那个“1”，
 * 它后面有多少个“0”，那么每个分组中的最后那个“1”后面就必须有相同数量的“0”。我们还是以下图为例，由于arr[7]是最后一个“1”，
 * 它后面只有1个“0”，那么根据上面我们描述的规律，就可以将数组 arr = [0,0,1,0,1,0,0,1,0] 拆分为[0,0,1,0]、[1,0]和[0,1,0]。
 * <p>
 * 然后，我们以最短分组长度为基准，从末尾开始校验每组中相对应位置的值是否相同，如果都相同，
 * 那么就满足了题目中所描述的“三等分”了。然后将[i, j]返回即可。
 */
public class _927_三等份 {
    public int[] threeEqualParts(int[] arr) {
        // 用于记录每个“1”所在arr中的位置（index）
        int[] record = new int[arr.length + 1];
        int oneCount = 0;
        for (int i = 0; i < arr.length; i++) if (arr[i] == 1) record[++oneCount] = i;
        // 如果数组全是0的情况，固定返回[0, arr.length - 1]
        if (oneCount == 0) return new int[]{0, arr.length - 1};
        // 如果1的个数不能被平分，则表示无法平均分配
        if (oneCount % 3 != 0) return new int[]{-1, -1};
        // gn：每组1的个数   lzn：最后一组末尾0的个数
        int gn = oneCount / 3, lzn = arr.length - 1 - record[oneCount];
        // 如果每组末尾0的个数不足，则返回[-1,-1]
        if (record[gn * 2] - record[gn] - 1 < lzn || record[gn * 3] - record[gn * 2] - 1 < lzn)
            return new int[]{-1, -1};
        int tail1 = record[gn] + lzn, tail2 = record[gn * 2] + lzn, tail3 = record[gn * 3] + lzn,
                nums = Math.min(Math.min(tail1 + 1, tail2 - tail1), tail3 - tail2);
        int[] result = new int[]{tail1, tail2 + 1};
        while (nums-- > 0) {
            if (arr[tail1] != arr[tail2] || arr[tail1] != arr[tail3] || arr[tail2] != arr[tail3])
                return new int[]{-1, -1};
            tail1--;
            tail2--;
            tail3--;
        }
        return result;
    }
}
