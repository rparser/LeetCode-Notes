class Solution {
    public int compareVersion(String version1, String version2) {
        int temp1, temp2;
        int i = 0, j = 0;

        // any of i and j is in the loop
        while (i < version1.length() || j < version2.length()) {
            temp1 = 0;
            temp2 = 0;
            // 依次比较每个.之前的片段
            while (i < version1.length() && '.' != version1.charAt(i)) {
                // '.001' == '0.1'
                temp1 = temp1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            i++;
            //
            while (j < version2.length() && '.' != version2.charAt(j)) {
                temp2 = temp2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            j++;
            // if equal then still in loop
            if (temp1 > temp2) return 1;
            if (temp1 < temp2) return -1;
        }
        return 0;

    }
}