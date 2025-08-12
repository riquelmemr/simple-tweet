package com.riquelmemr.simpletweet.service.tweet;

import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TweetService {
    void create(Tweet tweet);
    Tweet update(Long id, UpdateTweetRequest tweet, User user);
    void deleteById(Long id, User user);
    Tweet findById(Long id);
    List<Tweet> findByUserId(Long userId);
    Page<Tweet> findAll(int page, int pageSize);
}
