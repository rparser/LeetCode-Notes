package com.leetcode.AmazonOA;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class EventFire {
    class CallBack {
        String name;

        public CallBack() {
        }

        public CallBack(String name) {
            this.name = name;
        }

        public void call() {
            System.out.println("CallBack Event " + this.name + "is running now");
        }
    }

    List<CallBack> callbacks = new ArrayList<>();
    Lock lock = new ReentrantLock();
    boolean fired = false;

    void register(CallBack cb) {
        try {
            lock.lock();
            if (!fired) {
                callbacks.add(cb);
                return;
            }
        } finally {
            lock.unlock();
        }

        cb.call();
    }

    void event_fire() {
        try {
            lock.lock();
            fired = true;
        } finally {
            lock.unlock();
        }

        for (CallBack c : callbacks) {
            c.call();
        }
    }

    //方法2
    Queue<CallBack> eventQueue = new LinkedList<>();
    boolean isFired = false;

    public void reg_cb(CallBack cb) {
        if (!isFired) {
            eventQueue.offer(cb);
        } else {
            cb.call();
        }
    }

    public void fire() {
        while (!eventQueue.isEmpty()) {
            eventQueue.poll().call();
        }
        isFired = true;
    }
    //比如先执行fired=true再lock，之后要求分析这样会不会有问题。
    //with lock
    public void reg_cbwithlock(CallBack cb) {
        lock.lock();
        if (!isFired) {
            eventQueue.offer(cb);
            lock.unlock();
        } else {
            lock.unlock();
            cb.call();
        }
    }

    public void firewithlock() {
        lock.lock();
        isFired = true;
        while (!eventQueue.isEmpty()) {
            CallBack cb = eventQueue.poll();
            lock.unlock();
            cb.call();
            lock.lock();
        }
        lock.unlock();
    }

    //intern,有什么风险？
    public void reg_cbintern(CallBack cb) {
        lock.lock();
        if (!isFired) {
            eventQueue.offer(cb);
            lock.unlock();
        } else {
            lock.unlock();
            cb.call();
        }
    }

    public void fireintern() {
        lock.lock();
        isFired = true;
        lock.unlock();
        while (!eventQueue.isEmpty()) {
            CallBack cb = eventQueue.poll();
            cb.call();
        }
    }

    //intern2,风险？锁和没锁一样
    public void reg_cbintern2(CallBack cb) {
        lock.lock();
        if (!isFired) {
            eventQueue.offer(cb);
            lock.unlock();
        } else {
            lock.unlock();
            cb.call();
        }
    }

    public void fireintern2() {
        lock.lock();
        lock.unlock();
        isFired = true;
        while (!eventQueue.isEmpty()) {
            CallBack cb = eventQueue.poll();
            cb.call();
        }
    }

    //无风险版本

    /**
     * 这是一个barrier,可以在改完了isFired之后去尝试aquire lock，没拿到的话就会进入wait，所以这种改法是对的。这里开始没答好，
     * 我紧张了满脑子就记得lock是lock整个instance，然后完全没考虑如果没有去aquire lock，其实thread是不会wait的。
     * 比如如果fire method里面没有lock的话，他是可以在register被lock了的情况下还能执行的
     * 然后聊了聊如果要求keep order有什么想法，用两个queue，然后交换queue的时候lock，他说you are on a good track
     * 然后聊了聊不加锁实现thread safe，主要思路就是在register，在判断了isFired之后进去的时候判断queue为不为空，可以知道其他thread有没有call fired，并且有没有执行完。
     */
    public void reg_cbgood(CallBack cb) {
        lock.lock();
        if (!isFired) {
            eventQueue.offer(cb);
            lock.unlock();
        } else {
            lock.unlock();
            cb.call();
        }
    }

    public void firegood() {
        isFired = true;
        lock.lock();
        lock.unlock();
        while (!eventQueue.isEmpty()) {
            CallBack cb = eventQueue.poll();
            cb.call();
        }
    }

}
