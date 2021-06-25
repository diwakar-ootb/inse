package io.ootb.insefrontend.domain.repository;

import org.springframework.data.repository.CrudRepository;

import io.ootb.insefrontend.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}