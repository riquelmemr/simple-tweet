package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.service.like.LikeService;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class LikeFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    public void createOrDelete(String tweetId, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        likeService.createOrDelete(tweetId, user);
    }
}
