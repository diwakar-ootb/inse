package io.ootb.postsservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class PostResource {

	@GetMapping("/post/{message}")
	public String post(@PathVariable("message") String message) {
		
		
		
		return "";
	}
}
