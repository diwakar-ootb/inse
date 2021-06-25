package io.ootb.insefrontend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.ootb.insefrontend.util.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Authenticate the request to url /login by POST with json body '{ username,
 * password }'. If successful, response the client with header 'Authorization:
 * Bearer jwt-token'.
 *
 * @author shuaicj 2018/07/27
 */
@Slf4j
public class JwtUsernamePasswordAuthFilter extends OncePerRequestFilter {

	private final JwtConfig config;
	private final INSERealm realm;
	private final ObjectMapper mapper;

	@Autowired
	private JwtUtil jwtUtil;

	public JwtUsernamePasswordAuthFilter(JwtConfig config, INSERealm realm) {
		this.config = config;
		this.realm = (INSERealm) realm;
		this.mapper = new ObjectMapper();
	}

	@Override
	protected boolean isEnabled(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getMethod().equalsIgnoreCase("POST") && req.getServletPath().equals(config.getUrl());
	}

	@Override
	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException {
		// try authentication
		HttpServletResponse rsp = (HttpServletResponse) response;
		Subject subject = SecurityUtils.getSubject();
		try {
			User u = mapper.readValue(request.getInputStream(), User.class);
			subject.login(new UsernamePasswordToken(u.getUsername(), u.getPassword()));
		} catch (JsonProcessingException | AuthenticationException e) {
			log.error("authentication failed", e);
			rsp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		// generate jwt token
		io.ootb.insefrontend.domain.entity.User principal = (io.ootb.insefrontend.domain.entity.User) subject
				.getPrincipal();
		String token = jwtUtil.generateToken(principal);
		rsp.addHeader(config.getHeader(), config.getPrefix() + " " + token);
	}

	@Getter
	@Setter
	private static class User {
		private String username, password;
	}
}
