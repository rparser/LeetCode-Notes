
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int r = dimension.get(0);
        int c = dimension.get(1);

        //原理：从右上开始，如果找到0向下一行，否则向左直到不是0
        //flag判断是否找到
        boolean flag = false;
        int count = c - 1;
        for(int i = 0; i < r; i++)
            for(int j = count; j >= 0; j--){
                if(binaryMatrix.get(i,j) == 0) break;
                else {
                    //如果有1则变为true
                    flag = true;
                    count--;
                }
            }
        if(!flag) return -1;
        else return ++count;
    }
}