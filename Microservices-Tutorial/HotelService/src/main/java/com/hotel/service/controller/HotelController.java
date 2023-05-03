package com.hotel.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.service.entity.Hotel;
import com.hotel.service.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	// Creating Hotel

	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel savedHotel = hotelService.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
	}

	// Getting Single Hotel

	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable("hotelId") String hotelId) {
		Hotel hotel = hotelService.getHotel(hotelId);
		return ResponseEntity.ok(hotel);
	}

	// Getting All Hotel

	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel() {
		List<Hotel> allHotel = hotelService.getAllHotel();
		return ResponseEntity.ok(allHotel);
	}
}
