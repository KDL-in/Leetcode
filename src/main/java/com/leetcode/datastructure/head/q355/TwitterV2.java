package com.leetcode.datastructure.head.q355;

import java.util.*;


/*
面向对象设计和多个有序列表的合并
时间复杂度：post O(1) follow  unfollow O(1) getNews O(S log K)
        getNews中，遍历followers，O(K)，K为followers的数量。
        然后就是多个followers的有序tw列表的合并问题。时间复杂度为O(S log K)，其中S为K个follower的
        tw的数量，每次从大顶堆中poll出来开销为log K。这也是多有序列表合并比起排序高效的的部分——堆中的
        元素个数只有K个，而不是S个，排序则是O(S log S)
空间复杂度：O(N^2) + O(M)
        N个用户，每个用户都维护自己的follower列表，followers最多N个。
        M条tw
多个有序列表的合并算法非常有用，特别是在OS中的IO系统部分。
Runtime: 20 ms, faster than 99.90% of Java online submissions for Design Twitter.
Memory Usage: 45.1 MB, less than 34.49% of Java online submissions for Design Twitter.
*/
public class TwitterV2 {

    private static int timeStamp;
    private Map<Integer, User> users;

    public TwitterV2() {
        users = new HashMap<>();
    }


    public void postTweet(int userId, int tweetId) {
        User u = users.getOrDefault(userId, new User());
        u.post(new Tweet(tweetId));
        users.put(userId, u);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> r; Set<Integer> followers; Queue<Tweet> pq; Tweet tw; User cur;
        r = new ArrayList<>();pq = new PriorityQueue<>((a, b)->(b.time - a.time));

        if (users.containsKey(userId)) {
            cur = users.get(userId);
            if (cur.head.next!=null) pq.offer(cur.head.next);
            for (Integer fid : cur.followers) {
                if (users.containsKey(fid)) {
                    cur = users.get(fid);
                    if (cur.head.next!=null) pq.offer(users.get(fid).head.next);
                }
            }
        }

        while (!pq.isEmpty() && r.size() < 10) {
            tw = pq.poll();
            r.add(tw.tid);
            if (tw.next!=null)pq.offer(tw.next);
        }
        return r;
    }

    public void follow(int followerId, int followeeId) {
        if (followeeId ==followerId) return;
        User u = users.getOrDefault(followerId, new User());
        u.follow(followeeId);
        users.put(followerId, u);
    }

    public void unfollow(int followerId, int followeeId) {
        if (users.containsKey(followerId)) {
            users.get(followerId).unfollow(followeeId);
        }
    }

    public static void main(String[] args) {
        TwitterV2 twitter = new TwitterV2();
        twitter.postTweet(1,5);
        twitter.follow(1,1);
        twitter.getNewsFeed(1).forEach(System.out::println);
        System.out.println();
    }
    class Tweet {
        int tid, time;
        Tweet next;

        public Tweet(int tid) {
            this.tid = tid;
            time = timeStamp++;
        }

        public Tweet() {
        }
    }

    class User {
        private Tweet head;
        private Set<Integer> followers;

        public User() {
            head = new Tweet();
            followers = new HashSet<>();
        }

        public void post(Tweet tweet) {
            tweet.next = head.next;
            head.next = tweet;
        }

        public void follow(int followeeId) {
            followers.add(followeeId);
        }

        public void unfollow(int followeeId) {
            followers.remove(followeeId);
        }

    }
}
