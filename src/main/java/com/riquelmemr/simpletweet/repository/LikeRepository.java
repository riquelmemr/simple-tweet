package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.model.Like;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findByOwnerAndTweet(User user, Tweet tweet);
    long countByTweet(Tweet tweet);
}
