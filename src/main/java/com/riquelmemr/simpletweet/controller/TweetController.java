package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.dto.response.FeedResponse;
import com.riquelmemr.simpletweet.entities.Tweet;
import com.riquelmemr.simpletweet.facade.TweetFacade;
import com.riquelmemr.simpletweet.mapper.TweetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @Autowired
    private TweetMapper tweetMapper;

    @PostMapping("/create")
    public ResponseEntity<Tweet> createTweet(
            @RequestBody CreateTweetRequest request,
            JwtAuthenticationToken token) {
        Tweet tweet = tweetFacade.create(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTweet(
            @PathVariable String id,
            JwtAuthenticationToken token) {
        tweetFacade.delete(id, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tweet> updateTweet(
            @PathVariable String id,
            @RequestBody UpdateTweetRequest request,
            JwtAuthenticationToken token) {
        tweetFacade.update(id, request, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Tweet>> findByUser(@PathVariable String userId) {
        List<Tweet> tweets = tweetFacade.findByUserId(userId);
        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedResponse> getFeed(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Tweet> tweets = tweetFacade.getFeed(page, pageSize);
        return ResponseEntity.ok(tweetMapper.toFeedResponse(tweets));
    }
}
