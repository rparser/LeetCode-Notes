class Solution {
    class Node {
        int row;
        int col;
        int val;
    }

    public int maximumMinimumPath(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        //维持一个大顶堆
        PriorityQueue<Node> heap = new PriorityQueue<Node>((Node o1, Node o2) -> (o2.val - o1.val));
        boolean[][] mark = new boolean[m][n];
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Node n0 = new Node();
        n0.col = 0;
        n0.row = 0;
        n0.val = A[0][0];
        heap.add(n0);
        mark[0][0] = true;
        int res = A[0][0];
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            res = Math.min(cur.val, res);
            if (cur.col == n - 1 && cur.row == m - 1) {
                return res;
            }
            for (int k = 0; k < 4; k++) {
                int nx = cur.row + direction[k][0];
                int ny = cur.col + direction[k][1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !mark[nx][ny]) {
                    Node n1 = new Node();
                    n1.row = nx;
                    n1.col = ny;
                    n1.val = A[nx][ny];
                    heap.add(n1);
                    mark[nx][ny] = true;
                }
            }
        }
        return -1;
    }
}