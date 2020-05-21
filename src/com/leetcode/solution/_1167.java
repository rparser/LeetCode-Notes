class Solution {
    public int connectSticks(int[] sticks) {
        if (sticks.length == 1) {
            return 0;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int stick : sticks) {
            queue.add(stick);
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            Integer first = queue.poll();
            if (queue.isEmpty()) {
                break;
            }
            int second = queue.poll();
            int sum = first + second;
            ans += sum;
            queue.add(sum);
        }

        return ans;
    }
}