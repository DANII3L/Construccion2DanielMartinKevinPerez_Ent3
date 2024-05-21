package tdea.construccion2.app.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomerDetailsService customerDetailsService;
	private String username = null;
	Claims claims = null;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
	{
		 if(request.getServletPath().matches("/Home/login"))
			 filterChain.doFilter(request, response);
		 else {
			 String authorizationHeader = request.getHeader("Authorization");
			 String token = null;
			 
			 if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
			 {
				 token = authorizationHeader.substring(7);
				 username = jwtUtil.extractUserName(token);
				 claims = jwtUtil.extractAllClaims(token);
			 }
			 
			 if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customerDetailsService.loadUserByUsername(username); 
				if(jwtUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					new WebAuthenticationDetailsSource().buildDetails(request);
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				} 
			 }
			 filterChain.doFilter(request, response);
		 }
	}
	
	public Boolean isAdmin() {
		return "Admin".equalsIgnoreCase((String) claims.get("role"));
	}
	
	public Boolean isVeterinary() {
		return "Veterinario".equalsIgnoreCase((String) claims.get("role"));
	}
	
	public Boolean isSeller() {
		return "Vendedor".equalsIgnoreCase((String) claims.get("role"));
	}
	
	public Boolean isOwner() {
		return "Dueño".equalsIgnoreCase((String) claims.get("role"));
	}
	
	public String getCurrentUser() {
		return username;
	}

	public JwtUtil getJwtUtil() {
		return jwtUtil;
	}

	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public CustomerDetailsService getCustomerDetailsService() {
		return customerDetailsService;
	}

	public void setCustomerDetailsService(CustomerDetailsService customerDetailsService) {
		this.customerDetailsService = customerDetailsService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}
}
