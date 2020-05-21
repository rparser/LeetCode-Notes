class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            // 先判断是否可以整除 15.
            if (i % 15 == 0) {
                result.add("FizzBuzz");
                // 实际判断是否可以整除3或者5，是没有先后顺序的，这里选择先判断3.
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                // 都无法整除15，3 和 5， 属于正常数，直接加入结果。
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}