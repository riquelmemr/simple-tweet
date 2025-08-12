package com.riquelmemr.simpletweet.controller;

import com.riquelmemr.simpletweet.dto.response.LikeDetailResponse;
import com.riquelmemr.simpletweet.facade.LikeFacade;
import com.riquelmemr.simpletweet.mapper.LikeMapper;
import com.riquelmemr.simpletweet.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class LikeController extends BaseController {
    @Autowired
    private LikeFacade likeFacade;
    @Autowired
    private LikeMapper likeMapper;

    @PostMapping("/{tweetId}/like")
    private ResponseEntity<Void> toggleLike(@PathVariable final Long tweetId,
                                            JwtAuthenticationToken jwtAuthenticationToken) {
        likeFacade.createOrDelete(tweetId, jwtAuthenticationToken);
        return handleResponse(HttpStatus.OK, null);
    }

    @GetMapping("/{tweetId}/likes")
    private ResponseEntity<List<LikeDetailResponse>> getLikesByTweet(@PathVariable final Long tweetId,
                                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                                     @RequestParam(value = "pageSize", defaultValue = "50") int pageSize) {
        Page<Like> likes = likeFacade.getLikesByTweet(tweetId, page, pageSize);
        return handleResponse(HttpStatus.OK, likeMapper.toLikeDetailResponseListDto(likes));
    }

}
