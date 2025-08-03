package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {
    List<Tweet> findByAuthorPk(UUID authorId);
}
