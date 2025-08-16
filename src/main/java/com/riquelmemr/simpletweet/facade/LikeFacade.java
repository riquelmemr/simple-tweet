package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.dto.response.LikeDetailResponse;
import com.riquelmemr.simpletweet.mapper.LikeMapper;
import com.riquelmemr.simpletweet.model.Like;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.service.like.LikeService;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikeFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private LikeMapper likeMapper;

    public void createOrDelete(Long tweetId, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        likeService.createOrDelete(tweetId, user);
    }

    public List<LikeDetailResponse> getLikesByTweet(Long tweetId, int page, int pageSize) {
        Page<Like> likes = likeService.findByTweet(tweetId, page, pageSize);
        return likeMapper.toLikeDetailResponseListDto(likes);
    }
}
