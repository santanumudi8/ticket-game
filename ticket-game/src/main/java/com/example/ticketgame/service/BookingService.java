package com.example.ticketgame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.ticketgame.entity.Booking;
import com.example.ticketgame.entity.Ticket;
import com.example.ticketgame.entity.User;
import com.example.ticketgame.repository.BookingRepository;
import com.example.ticketgame.repository.GameRepository;
import com.example.ticketgame.repository.UserRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	public String bookTicket(Booking booking) {
		
		if(!checkForAvailable(booking.getTicketId())) {
			return "This ticket has already booked!! Please choose another ticket.";
		}
		
		if(!payment(booking.getUserId())) {
			return "Insufficient Balance!!";
		}
		
		blockTicket(booking.getTicketId());
		
		bookingRepository.save(booking);
		
		String response = "Booking confirm";
		
		return response;
		
	}
	
	private boolean payment(Long userId) {
		
		Optional<User> userDetails = userRepository.findById(userId);
		User user = userDetails.get();
		if(user.getWalletBalance() < 5) {
			return false;
		}
		
		user.setWalletBalance(user.getWalletBalance() - 5);
		
		userRepository.save(user);
		return true;
	}

	private void blockTicket(Long ticketId) {
		
		Optional<Ticket> ticketDetails = gameRepository.findById(ticketId);
		Ticket ticket = ticketDetails.get();
		ticket.setAvailable(false);
		
		gameRepository.save(ticket);
		
	}
	
	private boolean checkForAvailable(Long ticketId) {
		
		Optional<Ticket> ticketDetails = gameRepository.findById(ticketId);
		
		if(ticketDetails.get().isAvailable()) {
			return true;
		}
		return false;
		
	}
	
}
