package com.leetcode.solution;

/**
 * 二分法查找
 * low,high两坐标，中值大于target则high=mid-1，中值小于target则low=mid+1
 */

public class _035SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length-1; //数组最大值
        while(low<=high){
            int mid = (low+high)/2; //10数组(0-9)返回mid 4(中值偏下)，9数组返回mid 4(中值)
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;

        //  使用Arrays.binarySearch
//      int res=Arrays.binarySearch(nums,target);
//      if(res<0) return -res-1;
//      else return res;
    }
}
