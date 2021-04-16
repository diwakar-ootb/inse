package io.ootb.schoolinfrontend.resources;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ootb.schoolinfrontend.domain.entity.User;
import io.ootb.schoolinfrontend.model.AuthResponse;
import io.ootb.schoolinfrontend.model.AuthUser;
import io.ootb.schoolinfrontend.security.MyUserDetailsService;
import io.ootb.schoolinfrontend.util.JwtUtil;

@RestController
public class AuthenticationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthUser authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		User subject = (User) SecurityUtils.getSubject();

		return ResponseEntity.ok(new AuthResponse(subject));
	}
}
