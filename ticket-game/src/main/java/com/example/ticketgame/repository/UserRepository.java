package com.example.ticketgame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ticketgame.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "select t.ticket_id, t.ticket_number from git.ticket t inner join git.booking b on t.ticket_id = b.ticket_id where b.user_id = :userId", nativeQuery = true)
	List<Object[]> getAllTicketsByUserId(Long userId);

}
