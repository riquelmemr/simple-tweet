package com.riquelmemr.simpletweet.security;

import com.riquelmemr.simpletweet.entities.Role;
import com.riquelmemr.simpletweet.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${jwt.expiration}")
    private Long jwtExpiresIn;

    @Autowired
    private JwtEncoder jwtEncoder;

    public JwtClaimsSet generateJwtClaimSet(User user) {
        Instant now = Instant.now();
        String scope = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        return JwtClaimsSet.builder()
                .issuer("simple-tweet-application")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtExpiresIn))
                .subject(user.getPk().toString())
                .claim("scope", scope)
                .build();
    }

    public String generateJwtToken(User user) {
        JwtClaimsSet jwtClaimsSet = generateJwtClaimSet(user);
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String getIdByToken(JwtAuthenticationToken token) {
        return token.getName();
    }

    public Long getJwtExpiresIn() {
        return jwtExpiresIn;
    }
}
