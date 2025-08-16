package com.riquelmemr.simpletweet.service.commentlike.impl;

import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.CommentLike;
import com.riquelmemr.simpletweet.model.User;
import com.riquelmemr.simpletweet.repository.CommentLikeRepository;
import com.riquelmemr.simpletweet.service.commentlike.CommentLikeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @Override
    @Transactional
    public void createOrDelete(Comment comment, User user) {
        Optional<CommentLike> existingCommentLike = commentLikeRepository.findByOwnerAndComment(user, comment);

        if (existingCommentLike.isPresent()) {
            commentLikeRepository.delete(existingCommentLike.get());
            return;
        }

        CommentLike commentLike = new CommentLike();
        commentLike.setComment(comment);
        commentLike.setOwner(user);
        commentLikeRepository.save(commentLike);
    }
}
