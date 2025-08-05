package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.request.CreateTweetRequest;
import com.riquelmemr.simpletweet.dto.request.UpdateTweetRequest;
import com.riquelmemr.simpletweet.dto.response.FeedResponse;
import com.riquelmemr.simpletweet.dto.response.TweetDetailResponse;
import com.riquelmemr.simpletweet.model.Tweet;
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
public class TweetController extends BaseController {
    @Autowired
    private TweetFacade tweetFacade;
    @Autowired
    private TweetMapper tweetMapper;

    @PostMapping("/create")
    public ResponseEntity<TweetDetailResponse> createTweet(@RequestBody CreateTweetRequest request, JwtAuthenticationToken token) {
        Tweet tweet = tweetFacade.create(request, token);
        return handleResponse(HttpStatus.CREATED, tweetMapper.toTweetDetailResponseDto(tweet));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable String id,
                                            JwtAuthenticationToken token) {
        tweetFacade.delete(id, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TweetDetailResponse> updateTweet(@PathVariable String id,
                                             @RequestBody UpdateTweetRequest request,
                                             JwtAuthenticationToken token) {
        Tweet tweet = tweetFacade.update(id, request, token);
        return handleResponse(HttpStatus.OK, tweetMapper.toTweetDetailResponseDto(tweet));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FeedResponse> findByUser(@PathVariable String userId) {
        List<Tweet> tweets = tweetFacade.findByUserId(userId);
        return handleResponse(HttpStatus.OK, tweetMapper.toFeedResponseDto(tweets));
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedResponse> getFeed(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Tweet> tweets = tweetFacade.getFeed(page, pageSize);
        return handleResponse(HttpStatus.OK, tweetMapper.toFeedResponseDto(tweets));
    }
}
