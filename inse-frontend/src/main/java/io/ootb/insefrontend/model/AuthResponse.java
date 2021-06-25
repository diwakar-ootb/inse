package io.ootb.insefrontend.model;

import io.ootb.insefrontend.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
	private final User jwt;
}
