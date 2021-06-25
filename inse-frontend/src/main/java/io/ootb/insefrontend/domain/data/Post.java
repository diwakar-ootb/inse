package io.ootb.insefrontend.domain.data;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "posts")
@Data
public class Post {

	@Id
	private String id;
	private String title;
	private String description;
	private String userId;
	private LocalTime createdDate;
	private LocalTime updatedDate;
}
