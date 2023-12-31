package com.example.ticketgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticketgame.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

	Booking findByTicketId(Long ticketId);

}
