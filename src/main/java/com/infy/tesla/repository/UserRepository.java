package com.infy.tesla.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infy.tesla.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByEmail(String email);

	 User findByFirstName(String username);
}
