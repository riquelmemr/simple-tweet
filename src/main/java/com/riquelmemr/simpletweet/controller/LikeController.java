package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.facade.LikeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController extends BaseController {
    @Autowired
    private LikeFacade likeFacade;

    @PostMapping("/{tweetId}")
    private ResponseEntity<Void> toggleLike(@PathVariable final String tweetId,
                                            JwtAuthenticationToken jwtAuthenticationToken) {
        likeFacade.createOrDelete(tweetId, jwtAuthenticationToken);
        return handleResponse(HttpStatus.OK, null);
    }
}
