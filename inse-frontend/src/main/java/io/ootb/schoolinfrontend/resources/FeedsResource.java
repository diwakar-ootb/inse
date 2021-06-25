package io.ootb.insefrontend.resources;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ootb.insefrontend.domain.data.Post;
import io.ootb.insefrontend.domain.entity.User;
import io.ootb.insefrontend.domain.repository.PostsRepository;
import io.ootb.insefrontend.model.Feed;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/posts")
public class FeedsResource {

	@Autowired
	private final PostsRepository postsRepository;

	public FeedsResource(PostsRepository postsRepository) {
		this.postsRepository = postsRepository;
	}

	@GetMapping("/load")
	private Feed load() {
		return new Feed("Hello", "Description for hello");
	}

	@GetMapping(value = "/stream1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Feed> loadPhotoStream() {

		return Flux.range(1, 10).delayElements(Duration.ofMillis(250))
				.doOnNext(i -> System.out.println("processing in stream flow: " + i))
				.map(i -> new Feed("Hello " + i,
						"Bharti group-backed OneWeb on Monday said it has signed an MoU with Kazakhstan government and local partners to provide satellite connectivity "
								+ "services in Kazakhstan, commemorating the 60th anniversary of first human spaceflight. On April 12, 1961, Soviet cosmonaut "
								+ "Yuri Gagarin made history by becoming the first person to orbit the Earth.\r https://lnkd.in/devyNMe\r\n"
								+ "#oneweb #bhartiairtel #kazakhstan #india #ngso #satellitecommunications #spacenews #spacetechnology "
								+ i));
	}

	@GetMapping(value = "/stream")
	public List<Post> loadPostsStream() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		return postsRepository.findAllByUserIdOrderByUpdatedDate(principal.getId());
	}

	@PostMapping()
	public Post save(@RequestBody Post post) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		post.setUserId(principal.getId());

		Post save = postsRepository.save(post);

		return save;
	}

	@DeleteMapping()
	public Boolean delete(@RequestBody Post post) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User) authentication.getPrincipal();

		post.setUserId(principal.getId());
		post.setCreatedDate(LocalTime.now());
		post.setUpdatedDate(LocalTime.now());

		postsRepository.delete(post);

		return Boolean.TRUE;
	}
}