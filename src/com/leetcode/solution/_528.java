class Solution {
    private double[] prePrbblty = null;
    private Random random = new Random();

    public Solution(int[] w) {
        double sum = 0;
        for (int i = 0; i < w.length; i++)    //求权重总和
            sum += w[i];

        prePrbblty = new double[w.length];

        for (int i = 0; i < w.length; i++)    //求概率前缀和
            prePrbblty[i] = w[i] + (i == 0 ? 0 : prePrbblty[i - 1]);

        for (int i = 0; i < w.length; i++)    //化成分数
            prePrbblty[i] /= sum;
    }

    public int pickIndex() {
        double rd = random.nextDouble();    //生成随机数

        //二分查找找第一个小于等于随机数的前缀和
        int l = 0, r = prePrbblty.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;

            if (prePrbblty[mid] <= rd) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        if (prePrbblty[l] > rd)    //如果没有比随机数小的，说明选中下标0
            return 0;
        else
            return l + 1;
    }
}
