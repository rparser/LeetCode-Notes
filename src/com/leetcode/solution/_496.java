class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] results = new int[nums1.length];
        int index = 0;
        int j = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            int inDex = map.get(nums1[i]);  //找出nums1元素在nums2中的位置，然后向右方向判断
            for (j = inDex + 1; j < nums2.length; j++) {
                if (nums1[i] < nums2[j]) {
                    results[index++] = nums2[j];
                    break;
                }
            }
            if (j >= nums2.length) results[index++] = -1;
        }
        return results;
    }
}
