public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int count = 0;
        int temp;
        char[] tempArr = new char[4];
        int tempIndex = 0;
        while (count < n) {
            temp = read4(tempArr);
            if (temp == 0) break;
            while (tempIndex < temp && count < n) {
                buf[count] = tempArr[tempIndex];
                tempIndex++;
                count++;
            }
            tempIndex = 0;
        }
        return count;
    }
}