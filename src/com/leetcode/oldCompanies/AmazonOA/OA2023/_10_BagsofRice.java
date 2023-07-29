package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class _10_BagsofRice {
    // 计算可能的最大完美米袋集合大小
    public static int maxSetSize2(int[] riceBags) {
        Arrays.sort(riceBags); // 对riceBags数组进行排序，便于后续找到完美的米袋集合
        Set<Integer> set = Arrays.stream(riceBags).boxed().collect(Collectors.toSet());

        int maxSetSize = -1;

        for (int riceBag : riceBags) {
            int temp = riceBag;
            int cur = 0;
            while (set.contains(temp) && temp != 1) {
                cur++;
                set.remove(temp);
                temp = temp * temp;
                maxSetSize = Math.max(maxSetSize, cur);
            }

        }

        return maxSetSize;
    }


    public static int maxSetSize(int[] riceBags) {
        Arrays.sort(riceBags); // 对riceBags数组进行排序，便于后续二分查找

        int maxSetSize = -1;
        for (int i = 0; i < riceBags.length; i++) {
            int setSize = formPerfectSet(riceBags, i);
            if (setSize >= 2) {
                maxSetSize = Math.max(maxSetSize, setSize);
            }
        }

        return maxSetSize;
    }

    private static int formPerfectSet(int[] riceBags, int startIndex) {
        int setSize = 1; // 至少包含当前米袋
        int current = riceBags[startIndex];
        while (true) {
            int next = current * current;
            int nextIndex = binarySearch(riceBags, startIndex + 1, riceBags.length, next);
            if (nextIndex < 0) {
                break; // 没有找到符合条件的下一个元素，跳出循环
            }
            setSize++;
            startIndex = nextIndex; // 找到了符合条件的下一个元素，继续循环
            current = next;
        }
        return setSize;
    }

    private static int binarySearch(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid; // 找到了目标元素
            } else if (arr[mid] < target) {
                left = mid + 1; // 目标元素在右半部分
            } else {
                right = mid; // 目标元素在左半部分
            }
        }
        return -1; // 没有找到目标元素
    }
    public static void main(String[] args) {
        int[] riceBags = {3, 9, 4, 2, 16};
        int result = maxSetSize(riceBags);
        System.out.println("可能的最大完美米袋集合大小: " + result);
        int[] riceBags2 = {1};
        int result2 = maxSetSize(riceBags2);
        System.out.println("可能的最大完美米袋集合大小: " + result2);
        int[] riceBags3 = {4, 32, 16, 2, 8, 64, 512, 128, 256};
        int result3 = maxSetSize(riceBags3);
        System.out.println("可能的最大完美米袋集合大小: " + result3);
    }
}
