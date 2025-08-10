package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.model.Like;
import com.riquelmemr.simpletweet.model.Tweet;
import com.riquelmemr.simpletweet.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByOwnerAndTweet(User user, Tweet tweet);
    long countByTweet(Tweet tweet);
    Page<Like> findByTweet(Tweet tweet, Pageable pageable);
}
