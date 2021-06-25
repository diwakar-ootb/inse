package io.ootb.insefrontend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.ootb.insefrontend.domain.dao.UserDAO;
import io.ootb.insefrontend.domain.entity.User;
import io.ootb.insefrontend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Authenticate requests with header 'Authorization: Bearer jwt-token'.
 *
 * @author shuaicj 2018/08/03
 */
@Slf4j
public class JwtTokenAuthFilter extends OncePerRequestFilter {

	private final JwtConfig config;

	private JwtUtil jwtUtil;

	private UserDAO userDAO;

	public JwtTokenAuthFilter(JwtConfig config, JwtUtil jwtUtil) {
		this.config = config;
		this.jwtUtil = jwtUtil;
	}

	protected void doFilterInternal1(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		String token = req.getHeader(config.getHeader());
		if (token != null && token.startsWith(config.getPrefix() + " ")) {
			token = token.replace(config.getPrefix() + " ", "");
			Claims claims = null;
			// verify token
			try {
				claims = Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(token).getBody();
			} catch (Exception e) {
				log.warn("jwt token verification failed", e);
			}
			// login automatically
			if (claims != null) {
				String principal = claims.getSubject();
				List<String> roles = new ArrayList<>(); // claims.get("roles", List.class);
				List<String> permissions = new ArrayList<>(); // claims.get("permissions", List.class);
				try {
					SecurityUtils.getSubject().login(new JwtToken(principal, token, roles, permissions));
				} catch (AuthenticationException e) {
					log.error("authentication failed", e);
					rsp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
			}
		}
		chain.doFilter(req, rsp);
	}

	@Override
	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		String authorizationHeader = req.getHeader("Authorization");

		if (authorizationHeader == null) {
			authorizationHeader = request.getParameter("Authorization");
		}
		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			Optional<User> userDetails = this.userDAO.findByUsername(username);

			if (jwtUtil.validateToken(jwt, userDetails.get())) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails.get(), null, userDetails.get().getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(req, rsp);
	}
}
