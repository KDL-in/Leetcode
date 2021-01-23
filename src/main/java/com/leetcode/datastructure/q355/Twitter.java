package com.leetcode.datastructure.q355;

import java.util.*;


/*
* 355. Design Twitter
* 设计twitter
* https://leetcode.com/problems/design-twitter/
*
* */

/*
分析：
tw的顺序保证可以用关键字也可以用list的天然顺序。
followers列表问题，可以用map套set，我认为O（N^2）是不可避免的，因为指向n对n的列表，本身就需要这个开销
map套list不太好，因为需要快速删除的添加。
Runtime: 44 ms, faster than 21.41% of Java online submissions for Design Twitter.
Memory Usage: 45.4 MB, less than 27.65% of Java online submissions for Design Twitter.
*/

class Twitter {

    private Map<Integer, Set<Integer>> fMap; // uid -> {uid, ..} follow list
    private List<Tweet> tweets;

    /** Initialize your data structure here. */
    public Twitter() {
        fMap = new HashMap<>();
        tweets = new ArrayList<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!fMap.containsKey(userId)) {
            Set<Integer> set = new HashSet<>();
            set.add(userId);
            fMap.put(userId, set);
        }
        tweets.add(new Tweet(userId, tweetId));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if (!fMap.containsKey(userId)) return new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        Set<Integer> followers = fMap.get(userId);
        for (int i = tweets.size()-1; i >= 0 &&  r.size() < 10; i--) {
            Tweet tw = tweets.get(i);
            if (followers.contains(tw.uid)) r.add(tw.tid);
        }
        return r;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> set;
        if (!fMap.containsKey(followerId)) {
            set = new HashSet<>();
            set.add(followerId);
            fMap.put(followerId, set);
        } else {
            set = fMap.get(followerId);
        }
        set.add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId!=followeeId && fMap.containsKey(followerId)) {
            fMap.get(followerId).remove(followeeId);
        }
    }


    class Tweet {
        int uid, tid;
        public Tweet(int uid, int tid) {
            this.uid = uid;
            this.tid = tid;
        }
    }
}
