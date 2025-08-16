package com.riquelmemr.simpletweet.dto.response;

import com.riquelmemr.simpletweet.model.Like;

import java.util.Date;
import java.util.List;

public record TweetDetailResponse(Long id,
                                   String content,
                                   String username,
                                   String name,
                                   List<Like> likes,
                                   Date creationTime) {
}
