package io.ootb.insefrontend.domain.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import io.ootb.insefrontend.domain.entity.User;

@Repository
public class UserDAO extends AbstractDAO<User> {

	public Optional<User> findByEmail(String email) {
		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		criteria.where(builder.equal(userRoot.get("email"), email));
		List<User> users = getEm().createQuery(criteria).getResultList();
		if (users.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(users.get(0));
		}
	}

	public Optional<User> findByUsername(String username) {
		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);
		criteria.where(builder.equal(userRoot.get("username"), username));
		List<User> users = getEm().createQuery(criteria).getResultList();
		if (users.size() == 0) {
			return Optional.empty();
		} else {
			return Optional.of(users.get(0));
		}
	}
}
