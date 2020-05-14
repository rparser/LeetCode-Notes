class Solution {
    public boolean isMonotonic(int[] A) {
        if(A.length < 2) return true;
        // flag判断趋势
        int flag = 0;
        for(int i = 1; i< A.length; i++){
            if(A[i] > A[i-1])
            {
                if(flag == 0) flag = 1;
                else if(flag == -1) return false;
            }
            else if(A[i] < A[i-1])
            {
                if(flag == 0) flag = -1;
                else if(flag == 1) return false;
            }
        }
        return true;
    }
}