package com.example.ticketgame.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.ticketgame.entity.User;
import com.example.ticketgame.model.TicketResponse;
import com.example.ticketgame.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public String createUser(User user) {
		
		userRepository.save(user);
		
		String response = "User added successfully";
		
		return response;
		
	}

	public List<TicketResponse> getAllTicketsByUserId(Long userId) {
		
		List<TicketResponse> response = new ArrayList<TicketResponse>();
		
		List<Object[]> allTickets = userRepository.getAllTicketsByUserId(userId);
		
		allTickets.forEach(item -> {
			TicketResponse ticketResponse = new TicketResponse();
			ticketResponse.setTicketId(((Long)item[0]).longValue());
			ticketResponse.setTicketNumber(Long.parseLong(String.valueOf(item[1])));
			response.add(ticketResponse);
		});
		
		return response;
	}

	
	
}
