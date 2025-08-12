package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.dto.request.CreateCommentRequest;
import com.riquelmemr.simpletweet.mapper.CommentMapper;
import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.service.comment.CommentService;
import com.riquelmemr.simpletweet.service.tweet.TweetService;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    public void create(Long tweetId, CreateCommentRequest request, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        Tweet tweet = tweetService.findById(tweetId);
        Comment comment = commentMapper.toModel(request, user, tweet);
        commentService.save(comment);
    }
}
