package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.request.CreateCommentRequest;
import com.riquelmemr.simpletweet.facade.CommentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweets/")
public class CommentController extends BaseController {
    @Autowired
    private CommentFacade commentFacade;

    @PostMapping("/{tweetId}/comments")
    private ResponseEntity<Void> createComment(@PathVariable final Long tweetId,
                                               @RequestBody final CreateCommentRequest dto,
                                               JwtAuthenticationToken token) {
        commentFacade.create(tweetId, dto, token);
        return handleResponse(HttpStatus.OK, null);
    }
}
