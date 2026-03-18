package com.security.app.config.security.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Decoders.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private static final String CLAIM_KEY_AUTHORITIES ="AUTHORITIES";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key to check that token is valid
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getPayload();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //Add Authorities tp the JWT token payload (will be also cover by the signature)
        claims.put(CLAIM_KEY_AUTHORITIES,userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token){
        List<GrantedAuthority> authoList=new ArrayList<>();
        //Split to extract payload
        String[] jwt_part=token.split("[.]");

        if(jwt_part.length >1){
            //TextCodec.BASE64URL.decodeToString(jwt_part[1]);
            //revert BAS64 payload
            String payload = Decoders.BASE64.decode( jwt_part[1]).toString();
            if (payload.charAt(0) == '{' && payload.charAt(payload.length() - 1) == '}') {
                //convert JSOn to MAP
                Map<String, Object> claimsMap = this.readValue(payload);
                //Retreive Authorities
                Object obj =claimsMap.get("AUTHORITIES");
                if(obj instanceof List){
                    for(Object objl: (List)obj){
                        if( objl instanceof Map){
                            String authorityValue= ((Map)objl).get("authority").toString();
                            if( authorityValue!= null){
                                //add Json authority to authList
                                authoList.add(new SimpleGrantedAuthority(authorityValue));
                            }

                        }
                    }
                }
                //System.out.println(claimsMap);
                //((String)((Map)((List)claimsMap.get("AUTHORITIES")).get(0)).get("authority"))
            }
        }
        return authoList;
    }

    protected Map<String, Object> readValue(String val) {
        try {
            return (Map)this.objectMapper.readValue(val, Map.class);
        } catch (IOException var3) {
            throw new MalformedJwtException("Unable to read JSON value: " + val, var3);
        }
    }
}
