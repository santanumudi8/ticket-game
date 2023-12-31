package com.example.ticketgame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ticketgame.entity.Ticket;

public interface GameRepository extends JpaRepository<Ticket, Long>{

	@Query(value = "select * from git.ticket where is_available = 1", nativeQuery = true)
	List<Ticket> getAllAvailableTickets();

	@Query(value = "select * from git.ticket where is_available = 0", nativeQuery = true)
	List<Ticket> getSoldTickets();

}
