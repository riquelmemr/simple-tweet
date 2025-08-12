package com.riquelmemr.simpletweet.service.comment;

import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.Tweet;
import org.springframework.data.domain.Page;

public interface CommentService {
    void save(Comment comment);
    Comment findById(Long id);
    Page<Comment> findByTweet(Tweet tweet, int page, int pageSize);
}
