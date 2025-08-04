package com.riquelmemr.simpletweet.config;

import com.riquelmemr.simpletweet.entities.Role;
import com.riquelmemr.simpletweet.entities.User;
import com.riquelmemr.simpletweet.enums.RoleEnum;
import com.riquelmemr.simpletweet.repository.RoleRepository;
import com.riquelmemr.simpletweet.repository.UserRepository;
import com.riquelmemr.simpletweet.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

import static com.riquelmemr.simpletweet.utils.ObjectUtils.isNotNull;

@Configuration
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByName(RoleEnum.ADMIN.name().toUpperCase());
        User adminUser = userService.findByUsername("admin");

        if (isNotNull(adminUser)) {
            System.out.println("User [" + adminUser.getUsername() + "] already exists.");
            return;
        }

        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@admin.com");
        user.setPassword(passwordEncoder.encode("nimda"));
        user.setRoles(Set.of(adminRole));
        userRepository.save(user);
    }
}
