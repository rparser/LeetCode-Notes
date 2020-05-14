// 线扫描法   来源公众号 @ 算法无遗策

//时间复杂度：O(NlogN) ，其中 NN 是建筑物的数目
class Solution {
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        // 创建返回值
        List<List<Integer>> res = new ArrayList<>();
        // 保存所有的可能拐点
        Set<Pair<Integer, Integer>> pairs = new TreeSet<>(
                (o1, o2) -> !o1.getKey().equals(o2.getKey()) ? o1.getKey() - o2.getKey() : o1.getValue() - o2.getValue()); // 二元组
        // 将每一个建筑分成两个部分
        for (int[] build : buildings) {
            pairs.add(new Pair<>(build[0], -build[2]));
            pairs.add(new Pair<>(build[1], build[2]));
        }
        // 优先队列的最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1); // 最大堆
        // 记录之前的高度
        int prev = 0;
        // 遍历
        for (Pair<Integer, Integer> pair : pairs) {
            if (pair.getValue() < 0) queue.offer(-pair.getValue()); // 左端点 高度入堆
            else queue.remove(pair.getValue()); // 右端点 高度出堆
            Integer cur = queue.peek() == null ? 0 : queue.peek(); // 获取最大堆的当前顶点，当null时置为0
            if (prev != cur) {
                res.add(new ArrayList<Integer>() {{
                    add(pair.getKey());
                    add(cur);
                }});
                prev = cur;
            }
        }
        return res;
    }
}