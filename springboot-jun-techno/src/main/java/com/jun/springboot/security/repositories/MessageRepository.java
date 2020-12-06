package com.jun.springboot.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jun.springboot.security.entities.Message;

/**
 * @author Junaedi Harmiansyah
 *
 */
public interface MessageRepository extends JpaRepository<Message, Integer>{

}
