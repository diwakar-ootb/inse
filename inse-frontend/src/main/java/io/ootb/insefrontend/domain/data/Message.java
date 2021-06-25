package io.ootb.insefrontend.domain.data;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "messages")
@Data
public class Message {

	@Id
	private String id;
	private String subject;
	private String body;
	private String type;
	private String fromUserId;
	private String userId;
	private LocalTime createdDate;
	private LocalTime updatedDate;
}
