package com.riquelmemr.simpletweet.service.like;

import com.riquelmemr.simpletweet.model.Like;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.domain.Page;

public interface LikeService {
    void createOrDelete(Long tweetId, User user);
    long countLikesForTweet(Tweet tweet);
    Page<Like> findByTweet(Long tweetId, int page, int pageSize);
}
