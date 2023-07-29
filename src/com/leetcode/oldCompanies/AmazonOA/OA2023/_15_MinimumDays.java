package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.HashSet;

public class _15_MinimumDays {
    public static int getMinimumDays2(int[] parcels) {
        int totalParcels = 0;
        int maxParcels = 0;

        // 计算总包裹数量和最大包裹数量
        for (int parcel : parcels) {
            totalParcels += parcel;
            maxParcels = Math.max(maxParcels, parcel);
        }

        // 使用二分查找寻找最小的x
        int left = 1;
        int right = maxParcels;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int days = getDaysNeeded(parcels, mid);
            if (days > totalParcels) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int getDaysNeeded(int[] parcels, int x) {
        int days = 0;
        for (int parcel : parcels) {
            days += (parcel + x - 1) / x;
        }
        return days;
    }

    public static int getMinimumDays(int[] parcels) {
        HashSet<Integer> uniqueParcels = new HashSet<>();
        for (int parcel : parcels) {
            if (parcel > 0) {
                uniqueParcels.add(parcel);
            }
        }
        return uniqueParcels.size();
    }

    public static void main(String[] args) {
        int[] parcels = {2, 3, 4, 3, 3};
        int result = getMinimumDays(parcels);
        System.out.println("派送所有包裹所需的最少天数: " + result); // 输出：3
    }
}
