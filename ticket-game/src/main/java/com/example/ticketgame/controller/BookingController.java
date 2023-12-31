package com.example.ticketgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketgame.entity.Booking;
import com.example.ticketgame.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@PostMapping("/booking-ticket")
	public String bookTicket(@RequestBody Booking booking) {
		
		String response = bookingService.bookTicket(booking);
		
		return response;
	}
	
}
