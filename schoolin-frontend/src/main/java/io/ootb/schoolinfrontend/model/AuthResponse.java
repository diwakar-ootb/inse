package io.ootb.schoolinfrontend.model;

import io.ootb.schoolinfrontend.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
	private final User jwt;
}
