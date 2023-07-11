// 线扫描法   来源公众号 @ 算法无遗策

import javafx.util.*;

import java.util.*;


class _218_the_skyline_problem {
    // 扫描线Scan
    //时间复杂度：O(NlogN) ，其中 N 是建筑物的数目, 空间复杂度：O(N) 。需要额外 O(n) 的空间来保存结果。
    // 每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new TreeMap<>();

        for (int[] build : buildings) {
            //插入左节点，负值，变成list
            if (!map.containsKey(build[0]))
                map.put(build[0], new ArrayList<>());
            map.get(build[0]).add(-build[2]);
            //插入右节点，变成list
            if (!map.containsKey(build[1]))
                map.put(build[1], new ArrayList<>());
            map.get(build[1]).add(build[2]);
        }

        //保留当前位置的所有高度 重定义排序：从大到小 (K高度 ，value 次数)
        Map<Integer, Integer> heights = new TreeMap<>((o1, o2) -> o2 - o1);
        //保留上一个位置的横坐标及高度
        int lastHeight = 0;
        // key是所有有可能转折的横坐标
        for (int key : map.keySet()) {
            List<Integer> yList = map.get(key);
            //排序 (NlogN),从小到大，左先进
            Collections.sort(yList);

            for (int y : yList) {
                //左端点,高度入队,次数+1
                if (y < 0) {
                    int val = heights.getOrDefault(-y, 0);
                    heights.put(-y, val + 1);
                } else {
                    //右端点移除高度，次数-1（如果次数为1则删除）
                    int val = heights.getOrDefault(y, 0);
                    if (val == 1)
                        heights.remove(y);
                    else
                        heights.put(y, val - 1);
                }
                //获取heights的最大值:就是第一个值
                Integer curMax = 0;
                if (!heights.isEmpty())// 保证此时有height
                    curMax = heights.keySet().iterator().next();

                //如果当前最大高度不同于上一个高度，说明其为转折点
                if (lastHeight != curMax) {
                    //更新last，并加入结果集
                    lastHeight = curMax;
                    res.add(Arrays.asList(key, curMax));
                }
            }
        }
        return res;
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // 保存所有的可能拐点, Set不重复的排序,先比较key再比较value
        Set<Pair<Integer, Integer>> pairs = new TreeSet<>(
                (o1, o2) -> !o1.getKey().equals(o2.getKey()) ? o1.getKey() - o2.getKey() : o1.getValue() - o2.getValue()); // 二元组
        // 将每一个建筑分成两个部分
        for (int[] build : buildings) {
            pairs.add(new Pair<>(build[0], -build[2]));
            pairs.add(new Pair<>(build[1], build[2]));
        }
        // 优先队列的最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 记录之前的高度
        int prev = 0;
        // 遍历
        for (Pair<Integer, Integer> pair : pairs) {
            if (pair.getValue() < 0)
                queue.offer(-pair.getValue()); // 左端点 高度入堆
            else
                queue.remove(pair.getValue()); // 右端点 高度出堆
            Integer cur = queue.peek() == null ? 0 : queue.peek(); // 获取最大堆的当前顶点，当null时置为0
            // 只有当prev和当前不同时才会加入结果
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