package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TweetRepository extends JpaRepository<Role, UUID> {
}
