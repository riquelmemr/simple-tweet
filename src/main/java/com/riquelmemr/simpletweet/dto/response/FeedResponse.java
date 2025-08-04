package com.riquelmemr.simpletweet.dto.response;

import java.util.List;

public record FeedResponse(List<FeedItemResponse> items,
                           int page,
                           int pageSize,
                           int totalPages,
                           long totalElements) {
}
