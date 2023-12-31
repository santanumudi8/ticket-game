package com.example.ticketgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketgame.entity.Ticket;
import com.example.ticketgame.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameService gameService;

	@PostMapping("/create-contest")
	public String createGame() {
		
		String response = gameService.createGame();
		
		return response;
	}
	
	@GetMapping("/get-all-tickets")
	public List<Ticket> getAllTickets(){
		
		List<Ticket> allTickets = gameService.getAllTickets();
		
		return allTickets;
	}
	
	@GetMapping("/get-all-available-tickets")
	public List<Ticket> getAllAvailableTickets(){
		
		List<Ticket> allAvailableTickets = gameService.getAllAvailableTickets();
		
		return allAvailableTickets;
	}
	
	@GetMapping("/play")
	public String playGame() {
		
		String response = gameService.playGame();
		return response;
	}
	
}
