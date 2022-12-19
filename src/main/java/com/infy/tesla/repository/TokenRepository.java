package com.infy.tesla.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infy.tesla.entity.Token;
import com.infy.tesla.entity.User;

public interface TokenRepository extends JpaRepository<Token, Long>
{
	Optional<Token> findByToken(String token);

	@Query(value = "select * from token t where t.user_id = :id",nativeQuery = true)
	Optional<Token> findByUserId(@Param("id") Long id);

}
