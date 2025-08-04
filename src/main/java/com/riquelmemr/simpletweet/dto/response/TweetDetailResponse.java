package com.riquelmemr.simpletweet.dto.response;

import java.util.Date;

public record TweetDetailResponse(String id,
                                   String content,
                                   String username,
                                   String name,
                                   Date creationTime) {
}
