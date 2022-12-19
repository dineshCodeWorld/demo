package com.infy.tesla.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infy.tesla.entity.Password;
import com.infy.tesla.entity.Token;

public interface PasswordRepository extends JpaRepository<Password, Long>
{
	Optional<Password> findByToken(String token);
	
	@Query(value = "select * from password p where p.user_id = :id",nativeQuery = true)
	Optional<Password> findByUserId(@Param("id") Long id);
}
