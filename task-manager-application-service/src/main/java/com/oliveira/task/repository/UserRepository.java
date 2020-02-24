package com.oliveira.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oliveira.task.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * @param name
	 * @return user
	 */
	User findByName(String name);

}
