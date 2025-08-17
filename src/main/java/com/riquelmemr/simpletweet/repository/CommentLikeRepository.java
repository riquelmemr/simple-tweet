package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.model.Comment;
import com.riquelmemr.simpletweet.model.CommentLike;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByOwnerAndComment(User user, Comment comment);
}
