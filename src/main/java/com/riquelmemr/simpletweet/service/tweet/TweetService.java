package com.riquelmemr.simpletweet.service.tweet;

import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TweetService {
    void create(Tweet tweet, User user);
    Tweet update(String id, UpdateTweetRequest tweet, User user);
    void deleteById(String id, User user);
    Tweet findById(String id);
    List<Tweet> findByUserId(String userId);
    Page<Tweet> findAll(int page, int pageSize);
}
