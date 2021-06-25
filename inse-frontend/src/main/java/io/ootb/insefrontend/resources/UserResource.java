package io.ootb.insefrontend.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserResource {

	@GetMapping("register")
	private String register() {
		
		return "registering user";
	}
}
