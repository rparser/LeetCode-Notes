import java.util.*;

// 时间复杂度O(NK)移除时搜索元素 : O(NlogK)插入 -
// O(log(n)) time for the enqueing and dequeing methods (offer, poll, remove() and add); linear time for the remove(Object) and contains(Object) methods;

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        PriorityQueue<Double> minHeap = new PriorityQueue<>(); //保存较大的一半
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); //保存较小的一半
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer((double) nums[i]);

            // 移除刚离开窗口的值
            if (minHeap.size() + maxHeap.size() > k) {
                // 如果不在minHeap，则从maxHeap移除
                if (minHeap.contains((double) nums[i - k]))
                    minHeap.remove((double) nums[i - k]);
                else
                    maxHeap.remove((double) nums[i - k]);
            }

            // 平衡堆，min可能比max +3 ~ -1
            if (minHeap.size() - maxHeap.size() > 1)
                maxHeap.offer(minHeap.poll());
            // 此时，min可能比max +1 ~ -1,如果需要，则从minHeap取最小值给maxHeap
            if (maxHeap.size() > 0 && minHeap.size() > 0 && maxHeap.peek() > minHeap.peek())
                maxHeap.offer(minHeap.poll());
            // 此时，min可能比max +1 ~ -3
            if (maxHeap.size() - minHeap.size() > 2)
                minHeap.offer(maxHeap.poll());

            // 求窗口中间值
            if (minHeap.size() + maxHeap.size() == k) {
                if (maxHeap.size() - minHeap.size() == 1)
                    res[index++] = maxHeap.peek();
                else if (minHeap.size() - maxHeap.size() == 1)
                    res[index++] = minHeap.peek();
                else
                    res[index++] = (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
        return res;
    }
}