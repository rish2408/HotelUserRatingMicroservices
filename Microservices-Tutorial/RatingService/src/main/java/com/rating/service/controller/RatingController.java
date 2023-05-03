package com.rating.service.controller;

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

import com.rating.service.entities.Rating;
import com.rating.service.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;

	// Create Rating
	
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
	}
	
	// Get All Ratings
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings()
	{
		return ResponseEntity.ok(ratingService.getAllRating());
	}
	
	// Get Rating By UserId
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") String userId)
	{
		return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
	}
	
	// Get Rating By HotelId
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("hotelId") String hotelId)
	{
		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}
	
}
