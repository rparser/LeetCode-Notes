package com.leetcode.oldCompanies.AmazonOA.OA2023;

public class _8_MinPage {
    public static int minPagesToRead(int[] pages, int days) {
        int maxPagesInOneDay = 0;

        // 计算总页数和最大页数
        for (int page : pages) {
            maxPagesInOneDay = Math.max(maxPagesInOneDay, page);
        }

        // 使用二分搜索查找最小的满足条件的x
        int left = 1;
        int right = maxPagesInOneDay;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 模拟学生每天阅读mid页的情况，检查是否能在规定的天数内完成所有章节的阅读
            if (isPossibleToReadAll(pages, mid, days)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    // 判断是否能在规定的天数内完成所有章节的阅读
    private static boolean isPossibleToReadAll(int[] pages, int x, int days) {
        int remainingDays = days;

        for (int page : pages) {
            int remainingPages = page;

            // 模拟学生每天阅读x页或至章节末尾的情况
            while (remainingPages > 0) {
                if (x <= remainingPages) {
                    remainingPages -= x;
                } else {
                    remainingPages = 0;
                }

                remainingDays--;
                if (remainingDays == 0 && remainingPages > 0) {
                    return false;
                }
            }
        }
        return remainingDays >= 0;
    }

    public static void main(String[] args) {
        int[] pages = {5, 3, 4};
        int days = 3;
        System.out.println(minPagesToRead(pages, days)); // 输出: 4
        int[] pages2 = {2, 4, 3};
        int days2 = 4;
        System.out.println(minPagesToRead(pages2, days2)); // 输出: 4
    }
}
