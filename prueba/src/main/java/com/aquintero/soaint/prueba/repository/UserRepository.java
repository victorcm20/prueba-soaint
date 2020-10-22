package com.aquintero.soaint.prueba.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aquintero.soaint.prueba.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.username = :username and u.password=:password")
	User findUsername(String username, String password);
}
