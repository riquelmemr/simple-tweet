package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.dto.response.FeedResponse;
import com.riquelmemr.simpletweet.dto.response.TweetDetailResponse;
import com.riquelmemr.simpletweet.mapper.TweetMapper;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.security.JwtUtils;
import com.riquelmemr.simpletweet.service.tweet.TweetService;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetFacade {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private TweetMapper tweetMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    public TweetDetailResponse create(CreateTweetRequest dto, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        Tweet tweet = tweetMapper.toModel(dto, user);
        tweetService.create(tweet);
        return tweetMapper.toTweetDetailResponseDto(tweet);
    }

    public void delete(Long id, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        tweetService.deleteById(id, user);
    }

    public TweetDetailResponse update(Long id, UpdateTweetRequest request, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        Tweet tweet = tweetService.update(id, request, user);
        return tweetMapper.toTweetDetailResponseDto(tweet);
    }

    public FeedResponse findByUserId(Long userId) {
        User user = userService.findById(userId);
        List<Tweet> tweets = tweetService.findByUserId(user.getPk());
        return tweetMapper.toFeedResponseDto(tweets);
    }

    public FeedResponse getFeed(int page, int pageSize) {
        Page<Tweet> tweets = tweetService.findAll(page, pageSize);
        return tweetMapper.toFeedResponseDto(tweets);
    }
}
