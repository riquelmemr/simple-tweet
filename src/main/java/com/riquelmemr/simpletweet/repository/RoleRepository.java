package com.riquelmemr.simpletweet.repository;

import com.riquelmemr.simpletweet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
