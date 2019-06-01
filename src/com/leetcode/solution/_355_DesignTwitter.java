package com.leetcode.solution;

import java.util.*;

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

public class _355_DesignTwitter {
    private static int timeStamp = 0;

    private Map<Integer, User> userMap;//user id : user

    private class Tweet { //用Linked List实现Tweet
        public int id;
        public int time;
        public Tweet next;

        public Tweet(int id) {
            this.id = id;
            time = timeStamp++;
            next = null;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public _355_DesignTwitter() {
        userMap = new HashMap<Integer, User>();
    }

    //User class实现post, follow, unfollow
    public class User {
        public int id;
        public Set<Integer> followed; //这个user follow的set
        public Tweet tweet_head;

        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            follow(id); // first follow itself, 因为需要自己的newsFeed
            tweet_head = null;
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            followed.remove(id);
        }

        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = tweet_head;
            tweet_head = t; //每次放到最前
        }
    }

    //post,follow,unfollow都要先检测是否存在user id
    public void postTweet(int userId, int tweetId) { //调用post
        if (!userMap.containsKey(userId)) { //检测是否用户存在
            User u = new User(userId);
            userMap.put(userId, u);
        }
        userMap.get(userId).post(tweetId);
    }

    public void follow(int followerId, int followeeId) { //调用follow
        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        if (!userMap.containsKey(followeeId)) {
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }
        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) { //调用unfollow
        if (!userMap.containsKey(followerId) || followerId == followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }

    // Best part of this.
    // first get all tweets lists from one user including itself and all people it followed.
    // Second add all heads into a max heap. Every time we poll a tweet with
    // largest time stamp from the heap, then we add its next tweet into the heap.
    // So after adding all heads we only need to add 9 tweets at most into this
    // heap before we get the 10 most recent tweet.
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new LinkedList<>();

        if (!userMap.containsKey(userId)) return result; //不存在user则返回

        Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Tweet> q = new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time)); //按照时间排序
        for (int user : users) {
            Tweet t = userMap.get(user).tweet_head;
            if (t != null) q.add(t); //添加非空tweet
        }
        int n = 0;
        while (!q.isEmpty() && n < 10) { //队列为空或十次
            Tweet t = q.poll();
            result.add(t.id);
            n++;
            if (t.next != null) q.add(t.next); //添加非空tweet
        }
        return result;
    }
}
