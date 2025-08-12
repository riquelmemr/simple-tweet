package com.riquelmemr.simpletweet.service.comment.impl;

import com.riquelmemr.simpletweet.exceptions.EntityNotFoundException;
import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.repository.CommentRepository;
import com.riquelmemr.simpletweet.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id [" + id + "]"));
    }

    @Override
    public Page<Comment> findByTweet(Tweet tweet, int page, int pageSize) {
        return commentRepository.findByTweet(tweet, PageRequest.of(page, pageSize));
    }
}
