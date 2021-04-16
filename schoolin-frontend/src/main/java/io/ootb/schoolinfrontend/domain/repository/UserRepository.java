package io.ootb.schoolinfrontend.domain.repository;

import org.springframework.data.repository.CrudRepository;

import io.ootb.schoolinfrontend.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}