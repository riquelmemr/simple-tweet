package com.riquelmemr.simpletweet.service.comment.impl;

import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.repository.CommentRepository;
import com.riquelmemr.simpletweet.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
