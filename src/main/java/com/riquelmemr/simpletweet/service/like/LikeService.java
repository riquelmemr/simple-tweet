package com.riquelmemr.simpletweet.service.like;

import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;

public interface LikeService {
    void createOrDelete(String tweetId, User user);
    long countLikesForTweet(Tweet tweet);
}
