package com.example.ticketgame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.ticketgame.entity.Booking;
import com.example.ticketgame.entity.Ticket;
import com.example.ticketgame.entity.User;
import com.example.ticketgame.repository.BookingRepository;
import com.example.ticketgame.repository.GameRepository;
import com.example.ticketgame.repository.UserRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public String createGame() {
		
		List<Ticket> ticketList = new ArrayList<Ticket>();

		for(int i=1, ticketNumber = 12345; i<=5; i++,ticketNumber++) {
			Ticket ticket = new Ticket();
			ticket.setTicketNumber(Integer.toString(ticketNumber));
			ticket.setAvailable(true);
			ticketList.add(ticket);
		}
		
		gameRepository.saveAll(ticketList);
		
		String response = "Contest created successfully!!";
		return response;
	}

	public List<Ticket> getAllTickets() {
		
		List<Ticket> ticketList = gameRepository.findAll();
		
		return ticketList;
	}

	public List<Ticket> getAllAvailableTickets() {
		
		List<Ticket> availableTickets = gameRepository.getAllAvailableTickets();
		
		return availableTickets;
	}

	public String playGame() {
		
		List<Ticket> soldTickets = gameRepository.getSoldTickets();
		
		Random random = new Random();
		
		int index = random.nextInt(soldTickets.size());
		
		Ticket winnerTicket = soldTickets.get(index);
		
		User winnerUser =  getWinnerUserDetails(winnerTicket.getTicketId());
		
		winnerUser.setWalletBalance(winnerUser.getWalletBalance() + 50);
		
		userRepository.save(winnerUser);
		
		String response = winnerUser.getUserName()+" has won!! Prize amount has credited to you wallet balance";
	
		return response;
	}

	private User getWinnerUserDetails(Long ticketId) {

		Booking bookingDetails = bookingRepository.findByTicketId(ticketId);
		
		Long userId = bookingDetails.getUserId();
		
		Optional<User> userDetails = userRepository.findById(userId);
		
		return userDetails.get();
	}

	
	
}
