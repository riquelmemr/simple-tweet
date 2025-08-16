package com.riquelmemr.simpletweet.dto.response;

import java.util.List;

public record CommentResponse(Long id, String username, String name, String content, int likes, List<CommentResponse> replies) {
}
