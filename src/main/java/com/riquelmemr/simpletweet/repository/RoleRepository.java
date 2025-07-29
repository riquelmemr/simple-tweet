package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.entities.Role;
import com.riquelmemr.simpletweet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
