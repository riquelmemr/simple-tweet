package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateCommentRequest;
import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toModel(CreateCommentRequest request, User user, Tweet tweet) {
        Comment comment = new Comment();
        comment.setContent(request.content());
        comment.setTweet(tweet);
        comment.setOwner(user);
        return comment;
    }
}
