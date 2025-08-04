package com.riquelmemr.simpletweet.service.tweet;

import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.entities.Tweet;
import com.riquelmemr.simpletweet.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TweetService {
    void create(Tweet tweet, User user);
    void update(String id, UpdateTweetRequest tweet, User user);
    void deleteById(String id, User user);
    Tweet findById(String id);
    List<Tweet> findByUserId(String userId);
    Page<Tweet> findAll(int page, int pageSize);
}
