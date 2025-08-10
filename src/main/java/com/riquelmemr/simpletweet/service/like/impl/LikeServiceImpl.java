package com.riquelmemr.simpletweet.service.like.impl;

import com.riquelmemr.simpletweet.model.Like;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.repository.LikeRepository;
import com.riquelmemr.simpletweet.service.like.LikeService;
import com.riquelmemr.simpletweet.service.tweet.TweetService;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public void createOrDelete(Long tweetId, User user) {
        Tweet tweet = tweetService.findById(tweetId);

        Optional<Like> existingLike = likeRepository.findByOwnerAndTweet(user, tweet);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return;
        }

        Like like = new Like();
        like.setOwner(user);
        like.setTweet(tweet);
        likeRepository.save(like);
    }

    @Override
    public long countLikesForTweet(Tweet tweet) {
        return likeRepository.countByTweet(tweet);
    }

    @Override
    public Page<Like> findByTweet(Long tweetId, int page, int pageSize) {
        Tweet tweet = tweetService.findById(tweetId);
        return likeRepository.findByTweet(tweet, PageRequest.of(page, pageSize));
    }
}
