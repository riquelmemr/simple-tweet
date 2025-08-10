package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByAuthorPk(Long authorId);
}
