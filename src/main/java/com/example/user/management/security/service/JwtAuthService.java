package com.example.user.management.security.service;

import com.example.user.management.exception.custom.InvalidJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.example.user.management.security.consts.CustomJwtClaims.REFRESH_TOKEN_CLAIM;

@Service
@RequiredArgsConstructor
//TODO: Refactor JWT service, improve its interface
public class JwtAuthService {

    @Value("${user.config.jwt.secret-key-base-64}")
    private String key;

    @Value("${user.config.jwt.auth-token-lifespan-hours}")
    private int authTokenLifespan;

    @Value("${user.config.jwt.refresh-token-lifespan-hours}")
    private int refreshTokenLifespan;

    private final UserDetailsService userDetailsService;
    private final StringRedisTemplate redisTemplate;

    public String generateAuthToken(String username) {
        return generateToken(username, false, getDateWithHoursOffset(authTokenLifespan));
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, true, getDateWithHoursOffset(refreshTokenLifespan));
    }

    private String generateToken(String username, boolean isForRefresh, Date expiresAt) {
        return Jwts.builder()
                .subject(username)
                .expiration(expiresAt)
                .claim(REFRESH_TOKEN_CLAIM, isForRefresh)
                .signWith(getSecretKey())
                .compact();
    }

    private Date getDateWithHoursOffset(int offset) {
        LocalDateTime currentTime = LocalDateTime.now().plusHours(offset);
        return Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void validateAuthToken(String jwtToken) {
        Objects.requireNonNull(jwtToken);
        try {
            getClaims(jwtToken);
            if (isRefreshToken(jwtToken)) {
                throw new InvalidJwtTokenException("Invalid Token");
            }
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (JwtException e) {
            throw new InvalidJwtTokenException("Invalid Token");
        }
    }

    public boolean isRefreshToken(String jwtToken) {
        return Optional.ofNullable(getClaims(jwtToken).get(REFRESH_TOKEN_CLAIM, Boolean.class))
                .orElse(false);
    }

    public UserDetails getUserDetails(String jwtToken) {
        String username = getClaims(jwtToken).getSubject();
        return userDetailsService.loadUserByUsername(username);
    }

    private Claims getClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public boolean notBlackListed(String jwtToken) {
        return Objects.isNull(redisTemplate.opsForValue().get(jwtToken));
    }

}
