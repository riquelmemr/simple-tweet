package com.riquelmemr.simpletweet.dto.response;

import java.util.Date;

public record FeedItemResponse(Long id,
                               String content,
                               String username,
                               String name,
                               long likes,
                               long comments,
                               Date creationTime) {
}
