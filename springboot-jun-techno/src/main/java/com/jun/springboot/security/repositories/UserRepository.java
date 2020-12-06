package com.jun.springboot.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jun.springboot.security.entities.User;


/**
 * @author Junaedi Harmiansyah
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{

	Optional<User> findByEmail(String email);

}
