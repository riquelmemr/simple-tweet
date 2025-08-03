package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.entities.Tweet;
import com.riquelmemr.simpletweet.facade.TweetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    @Autowired
    private TweetFacade tweetFacade;

    @PostMapping("/create")
    public ResponseEntity<Tweet> createTweet(
            @RequestBody CreateTweetRequest createTweetRequest,
            JwtAuthenticationToken token) {
        Tweet tweet = tweetFacade.create(createTweetRequest, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTweet(
            @PathVariable String id,
            JwtAuthenticationToken token) {
        tweetFacade.delete(id, token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Tweet>> findByUser(@PathVariable String userId) {
        List<Tweet> tweets = tweetFacade.findByUserId(userId);
        return ResponseEntity.ok(tweets);
    }
}
