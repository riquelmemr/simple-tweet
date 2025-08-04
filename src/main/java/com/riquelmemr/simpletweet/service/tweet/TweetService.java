package com.riquelmemr.simpletweet.service.tweet;

import com.riquelmemr.simpletweet.entities.Tweet;
import com.riquelmemr.simpletweet.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TweetService {
    void create(Tweet tweet);
    void update(Tweet tweet);
    void deleteById(String id, User user);
    Tweet findById(String id);
    List<Tweet> findByUserId(String userId);
    Page<Tweet> findAll(int page, int pageSize);
}
