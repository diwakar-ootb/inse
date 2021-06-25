package io.ootb.insefrontend.domain.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import io.ootb.insefrontend.domain.data.Post;

@Repository
public interface PostsRepository extends MongoRepository<Post, String> {

	List<Post> findAllByUserIdOrderByUpdatedDate(String userId);

	// @Query("from Post p where (userId = :userId or userId in (select connectedTo
	// from Network where userId = :userId))")
	// @Query("{ 'userId' : ?0, 'userId' : }")
	@Query(value = "{ 'userId' : {$in : ?0 }}")
	List<Post> findAllByUserIdNetwork(String[] userId, Sort sort);
}
