package com.leetcode.solution;

public class _345_reverse_vowels_of_a_string {
    // O(N), O(N)
    public String reverseVowels(String s) {
        // 先将字符串转成字符数组（方便操作）
        char[] arr = s.toCharArray();
        int n = arr.length;
        int left = 0, right = n - 1;
        while (left < right) {
            // 左判断如果当前元素不是元音++
            while (left < n && isNotVowel(arr[left]))
                left++;

            // 右判断如果当前元素不是元音++
            while (right >= 0 && isNotVowel(arr[right]))
                right--;

            // 如果没有元音
            if (left >= right)
                break;

            // 交换前后的元音
            swap(arr, left, right);
            // 这里要分开写，不要写进数组里面去
            left++;
            right--;
        }
        // 最后返回的时候要转换成字符串输出
        return new String(arr);
    }

    private void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private boolean isNotVowel(char ch) {
        // 不是这些则不是元音返回true
        return ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u'
                && ch != 'A' && ch != 'E' && ch != 'I' && ch != 'O' && ch != 'U';
    }
}
