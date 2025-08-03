package com.riquelmemr.simpletweet.service.tweet;

import com.riquelmemr.simpletweet.entities.Tweet;
import com.riquelmemr.simpletweet.entities.User;

import java.util.List;

public interface TweetService {
    void create(Tweet tweet);
    void update(Tweet tweet);
    void deleteById(String id, User user);
    Tweet findById(String id);
    List<Tweet> findByUserId(String userId);
    List<Tweet> findByContent(String content);
}
