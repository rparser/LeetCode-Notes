/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int i = 0;
        //先找到一个人都不认识的人
        for (int j = 1; j < n; j++)
            if (knows(i, j))  i = j;
        //i可能是名人因为i不认识
        for (int k = 0; k < n; k++) {
            if (k == i) continue;
            // 如果有人认识他或他认识别人，则他不是名人
            if (knows(i, k) || !knows(k, i)) return -1;
        }
        return i;
    }
}