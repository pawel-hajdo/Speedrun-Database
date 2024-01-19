package com.speedrundatabaseapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JSON Web Tokens (JWT).
 *
 * <p>This class provides methods for generating and validating JWTs, as well as extracting
 * information from JWT claims, such as the username and expiration date.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
@Service
public class JwtService {

    private static String SECRET_KEY = System.getenv("ENCRYPTION_KEY");

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from a JWT token using a provided claims resolver function.
     *
     * @param token           The JWT token.
     * @param claimsResolver  The function to resolve the desired claim.
     * @param <T>             The type of the claim.
     * @return The extracted claim value.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT token for a given user.
     *
     * @param userDetails The user details for whom the token is generated.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with additional claims for a given user.
     *
     * @param extraClaims  Additional claims to be included in the token.
     * @param userDetails  The user details for whom the token is generated.
     * @return The generated JWT token.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 6)) //6 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates if a JWT token is valid for a given user.
     *
     * @param token        The JWT token to be validated.
     * @param userDetails  The user details for whom the token is validated.
     * @return True if the token is valid; false otherwise.
     */
    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
