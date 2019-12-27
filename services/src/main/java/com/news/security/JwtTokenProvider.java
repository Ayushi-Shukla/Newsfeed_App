package com.news.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {
	public static final Logger logger=LoggerFactory.getLogger(JwtTokenProvider.class);
private String secretKey="abcdefg";
private long exptime=86400;
public String generateToken(Authentication authentication)
{
	User user=(User) authentication.getPrincipal();
	
	return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).
			setExpiration(new Date((new Date()).getTime()+exptime*1000*100))
			.signWith(SignatureAlgorithm.HS512,secretKey).compact();
}
public boolean validateToken(String Token)
{
	try
	{
		Jwts.parser().setSigningKey(secretKey).parseClaimsJws(Token);
		return true;

	}
	catch (SignatureException e) {
        logger.error("Invalid JWT signature -> Message: {} ", e);
    } catch (MalformedJwtException e) {
        logger.error("Invalid JWT token -> Message: {}", e);
    } catch (ExpiredJwtException e) {
        logger.error("Expired JWT token -> Message: {}", e);
    } catch (UnsupportedJwtException e) {
        logger.error("Unsupported JWT token -> Message: {}", e);
    } catch (IllegalArgumentException e) {
        logger.error("JWT claims string is empty -> Message: {}", e);
    }
	return false;
	
}
public String getUsernameFromJwtToken(String token)
{
	return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
}

}
