package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.request.CreateCommentRequest;
import com.riquelmemr.simpletweet.dto.response.CommentResponse;
import com.riquelmemr.simpletweet.facade.CommentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets/")
public class CommentController extends BaseController {
    @Autowired
    private CommentFacade commentFacade;

    @PostMapping("/{tweetId}/comment")
    private ResponseEntity<Void> createComment(@PathVariable final Long tweetId,
                                               @RequestBody final CreateCommentRequest dto,
                                               JwtAuthenticationToken token) {
        commentFacade.create(tweetId, dto, token);
        return handleResponse(HttpStatus.OK, null);
    }

    @PostMapping("/comments/{commentId}/reply")
    private ResponseEntity<Void> replyComment(@PathVariable final Long commentId,
                                              @RequestBody final CreateCommentRequest dto,
                                              JwtAuthenticationToken token) {
        commentFacade.reply(commentId, dto, token);
        return handleResponse(HttpStatus.OK, null);
    }

    @GetMapping("/{tweetId}/comments")
    private ResponseEntity<List<CommentResponse>> getCommentsByTweet(@PathVariable final Long tweetId,
                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "pageSize", defaultValue = "50") int pageSize) {
        List<CommentResponse> commentResponses = commentFacade.getCommentsByTweet(tweetId, page, pageSize);
        return handleResponse(HttpStatus.OK, commentResponses);
    }
}
