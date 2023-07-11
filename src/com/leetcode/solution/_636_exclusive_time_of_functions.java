package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class _636_exclusive_time_of_functions {
    // 输入需要根据时间戳排序，输出是任务号排序
    static class Task {
        int id = 0;
        int time = 0;
        boolean isStart = true;

        Task(String[] log) {
            this.id = Integer.parseInt(log[0]);
            this.time = Integer.parseInt(log[2]);
            this.isStart = log[1].equals("start");
        }
    }

    // O(N), O(N) 任务不能交叉完成，考察前一个就好
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Task> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for (String log : logs) {
            Task task = new Task(log.split(":"));
            //任务开始推入stack
            if (task.isStart)
                stack.push(task);
                //如果这个任务是end
            else {
                Task last = stack.pop();
                int duration = task.time - last.time + 1;
                result[task.id] += duration;
                // 栈若不空，说明之前还有任务没有执行完成，那个任务的执行时间需要减去当前任务的执行时间
                if (!stack.isEmpty())
                    result[stack.peek().id] -= duration;
            }
        }
        return result;
    }
}
