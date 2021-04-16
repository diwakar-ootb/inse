package io.ootb.schoolinfrontend.resources;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ootb.schoolinfrontend.model.Feed;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/feeds")
public class FeedsResource {

	@GetMapping("/load")
	private Feed load() {

		return new Feed("Hello", "Description for hello");
	}

	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)	
	public Flux<Feed> loadPhotoStream() {

		return Flux.range(1, 10).delayElements(Duration.ofMillis(250))
				.doOnNext(i -> System.out.println("processing in stream flow: " + i))
				.map(i -> new Feed("Hello " + i, "Bharti group-backed OneWeb on Monday said it has signed an MoU with Kazakhstan government and local partners to provide satellite connectivity services in Kazakhstan, commemorating the 60th anniversary of first human spaceflight. On April 12, 1961, Soviet cosmonaut Yuri Gagarin made history by becoming the first person to orbit the Earth.\r\n"
						+ "https://lnkd.in/devyNMe\r\n"
						+ "#oneweb #bhartiairtel #kazakhstan #india #ngso #satellitecommunications #spacenews #spacetechnology " + i));
	}

	public Flux<Feed> findAll() {

		return Flux.interval(Duration.ofMillis(250)).onBackpressureDrop().map(this::generateFeed)
				.flatMapIterable(x -> x);

	}

	private List<Feed> generateFeed(long interval) {

		Feed obj = new Feed("Hello" + interval, "Description for hello" + interval);
		return Arrays.asList(obj);

	}
}