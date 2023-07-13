package com.leetcode.solution;

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */

class _362_Design_Hit_Counter {
    /**
     * 循环数组，记录指定秒数和总敲击次数
     **/
    private final int[][] cycle;
    /**
     * 最大秒数位置，如果这个数字 > 最大值301，实际表示位置为 head % 301
     **/
    private int head;
    /**
     * 最小秒数位置，数字规律同理head
     **/
    private int tail;

    /**
     * Initialize your data structure here.
     */
    public _362_Design_Hit_Counter() {
        // 301个秒帧，每秒记录2个数据，0：当前秒数，1：当前敲击次数
        cycle = new int[301][2];
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        // 最新秒数，如果秒数相同，则敲击次数+1，否则数组位置右移，敲击次数为之前敲击次数+1
        int[] item = cycle[head % 301];
        if (item[0] != timestamp) {
            int[] next = cycle[(++head) % 301];
            next[0] = timestamp;
            next[1] = item[1] + 1;
        } else {
            item[1]++;
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        // 用来和最大秒数求差的最小秒数
        int target = timestamp - 300;
        // 范围为 tail ~ head，如果tail很久没更新，head > tail + 300，则tail为head - 300（5分钟内，如果时其他时间，则对应修改这个数字）
        tail = Math.max(tail, head - 300);
        //head记录的时真是的数字，实际数据记录位置需要与数组长度取余
        int headReal = head % 301;
        //如果最大秒数页小于等于target，300s内无hit，则直接返回0
        if (target >= cycle[headReal][0]) {
            return 0;
        }
        // tail实际数组位置
        int tailReal = tail % 301;

        if (headReal < tailReal) { // 一轮循环，结构为 0---headReal---tailReal---300
            if (cycle[0][0] <= target) { // target在0 ~ headReal之间
                int newTail = findTail(0, headReal, target);
                tail = head - (headReal - newTail);
                return cycle[headReal][1] - cycle[newTail][1];
            } else { // target在tailReal ~ 300之间
                int newTail = findTail(tailReal, 300, target);
                tail = head - (headReal + 301 - newTail);
                return cycle[headReal][1] - cycle[newTail][1];
            }
        } else { // 无循环套圈，结构为 0---tail---head---300
            int newTail = findTail(tailReal, headReal, target);
            tail = head - (headReal - newTail);
            return cycle[headReal][1] - cycle[newTail][1];
        }
    }

    // 二分求target位置
    private int findTail(int left, int right, int target) {
        while (left < right - 1) {
            int middle = (left + right) / 2;
            if (cycle[middle][0] == target) {
                // 找到目标
                return middle;
            } else if (cycle[middle][0] > target) {
                right = middle;
            } else { // cycle[middle][0] < target
                left = middle;
            }
        }
        return left;
    }
}
