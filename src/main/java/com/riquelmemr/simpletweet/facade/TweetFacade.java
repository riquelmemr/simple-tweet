package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.mapper.TweetMapper;
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

    public Tweet create(CreateTweetRequest dto, JwtAuthenticationToken token) {
        Tweet tweet = tweetMapper.toModel(dto);
        User user = userService.extractUserFromToken(token);
        tweetService.create(tweet, user);
        return tweet;
    }

    public void delete(Long id, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        tweetService.deleteById(id, user);
    }

    public Tweet update(Long id, UpdateTweetRequest request, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        return tweetService.update(id, request, user);
    }

    public List<Tweet> findByUserId(Long userId) {
        User user = userService.findById(userId);
        return tweetService.findByUserId(user.getPk());
    }

    public Page<Tweet> getFeed(int page, int pageSize) {
        return tweetService.findAll(page, pageSize);
    }
}
