import java.util.*;

class MovingAverage {
    // O(1), O(N)
    private final Queue<Integer> q  = new LinkedList<>();
    private int sum;
    private final int size;

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        // 大小达到size了则先remove再offer
        if (q.size() == size)
            sum -= q.remove();

        q.offer(val);
        sum += val;
        return (double) sum / q.size();
    }
}