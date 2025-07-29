package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
