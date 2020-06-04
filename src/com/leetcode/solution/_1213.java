class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        int arr1Index = 0, arr2Index = 0, arr3Index = 0;
        int arr1Len = arr1.length, arr2Len = arr2.length, arr3Len = arr3.length;
        while (arr1Index < arr1Len && arr2Index < arr1Len && arr3Index < arr3Len) {
            int arr1Val = arr1[arr1Index], arr2Val = arr2[arr2Index], arr3Val = arr3[arr3Index];
            if (arr1Val == arr2Val && arr2Val == arr3Val) {
                res.add(arr1Val);
                arr1Index++;
                arr2Index++;
                arr3Index++;
                continue;
            }
            /// 最小值往后移动
            int minVal = Math.min(Math.min(arr1Val, arr2Val), arr3Val);
            if (arr1Val == minVal)
                arr1Index++;

            if (arr2Val == minVal)
                arr2Index++;

            if (arr3Val == minVal)
                arr3Index++;

        }
        return res;
    }
}