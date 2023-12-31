package com.example.ticketgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketgame.entity.Booking;
import com.example.ticketgame.entity.Ticket;
import com.example.ticketgame.entity.User;
import com.example.ticketgame.model.TicketResponse;
import com.example.ticketgame.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/create-account")
	public String createUser(@RequestBody User user) {
		
		String response = userService.createUser(user);
		
		return response;
	}
	
	
	@GetMapping(path = "/get-all-ticket/{userId}")
	public List<TicketResponse> getAllTicketsByUserId(@PathVariable(name = "userId") Long userId){
		
		List<TicketResponse> allTickets = userService.getAllTicketsByUserId(userId);
		
		return allTickets;
		
	}
	
}
