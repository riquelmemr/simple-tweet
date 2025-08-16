package com.riquelmemr.simpletweet.facade;

import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.service.comment.CommentService;
import com.riquelmemr.simpletweet.service.commentlike.CommentLikeService;
import com.riquelmemr.simpletweet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class CommentLikeFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentLikeService commentLikeService;

    public void toggleCommentLike(Long commentId, JwtAuthenticationToken token) {
        User user = userService.extractUserFromToken(token);
        Comment comment = commentService.findById(commentId);
        commentLikeService.createOrDelete(comment, user);
    }
}
