package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
