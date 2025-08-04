package com.riquelmemr.simpletweet.dto.response;

import java.util.Date;
import java.util.UUID;

public record FeedItemResponse(UUID id,
                               String content,
                               String username,
                               String name,
                               Date creationTime) {
}
