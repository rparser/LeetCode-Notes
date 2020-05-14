class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return (int) (o2 - o1);
            }
        });
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer((double) nums[i]);

            // 移除窗口外的值
            if (minHeap.size() + maxHeap.size() > k) {
                if (minHeap.contains((double) nums[i - k])) {
                    minHeap.remove((double) nums[i - k]);
                } else {
                    maxHeap.remove((double) nums[i - k]);
                }
            }

            // 平衡堆
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.offer(minHeap.poll());
            }
            if (maxHeap.size() > 0 && minHeap.size() > 0 && maxHeap.peek() > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
            }
            if (maxHeap.size() - minHeap.size() > 1) {
                minHeap.offer(maxHeap.poll());
            }

            // 求窗口中间值
            if (minHeap.size() + maxHeap.size() == k) {
                if (maxHeap.size() > minHeap.size()) {
                    res[index++] = maxHeap.peek();
                } else if (minHeap.size() > maxHeap.size()) {
                    res[index++] = minHeap.peek();
                } else {
                    res[index++] = (maxHeap.peek() + minHeap.peek()) / 2.0d;
                }
            }
        }
        return res;
    }
}