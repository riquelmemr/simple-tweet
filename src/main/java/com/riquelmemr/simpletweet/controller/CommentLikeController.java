package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.facade.CommentLikeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweets/comments")
public class CommentLikeController extends BaseController {
    @Autowired
    private CommentLikeFacade commentLikeFacade;

    @PostMapping("/{commentId}/likes")
    private ResponseEntity<Void> toggleCommentLike(@PathVariable final Long commentId,
                                                   JwtAuthenticationToken jwtAuthenticationToken) {
        commentLikeFacade.toggleCommentLike(commentId, jwtAuthenticationToken);
        return handleResponse(HttpStatus.OK, null);
    }
}
