package com.news.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.news.entity.*;
import com.news.service.*;
@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	@Autowired JwtTokenProvider jwtTokenprovider;
	@Autowired SignupService signupservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
				
		String jwtToken=getJwtToken(request);
		if(jwtToken!=null && jwtTokenprovider.validateToken(jwtToken) )
		{
			String username=jwtTokenprovider.getUsernameFromJwtToken(jwtToken);
			UserDetails userDetails=signupservice.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}
		}
		catch(Exception e)
		{
			logger.error("Can NOT set user authentication -> Message: {}", e);
		}
		filterchain.doFilter(request, response);
	}
	public String getJwtToken(HttpServletRequest request)
	{
		String authHeader=request.getHeader("Authorization");
		if(authHeader!=null && authHeader.startsWith("Bearer"))
		{	
			return authHeader.replace("Bearer ", "");

			
		}
		return null;

	}
}
