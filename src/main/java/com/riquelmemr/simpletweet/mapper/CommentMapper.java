package com.riquelmemr.simpletweet.mapper;

import com.riquelmemr.simpletweet.dto.request.CreateCommentRequest;
import com.riquelmemr.simpletweet.dto.response.CommentResponse;
import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CommentMapper {
    public Comment toModel(CreateCommentRequest request, User user, Tweet tweet) {
        Comment comment = new Comment();
        comment.setContent(request.content());
        comment.setTweet(tweet);
        comment.setOwner(user);
        return comment;
    }

    public Comment toModel(CreateCommentRequest request, User user, Comment parentComment) {
        Comment comment = new Comment();
        comment.setParentComment(parentComment);
        comment.setContent(request.content());
        comment.setOwner(user);
        return comment;
    }

    public CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getPk(),
                comment.getOwner().getUsername(),
                comment.getOwner().getName(),
                comment.getContent(),
                toListCommentResponse(comment.getReplies())
        );
    }

    public List<CommentResponse> toListCommentResponse(Set<Comment> comments) {
        return comments.stream().map(this::toCommentResponse).toList();
    }

    public List<CommentResponse> toListCommentResponse(Page<Comment> comments) {
        return comments.getContent().stream().map(this::toCommentResponse).toList();
    }
}
