package com.rating.service.services;

import java.util.List;

import com.rating.service.entities.Rating;

public interface RatingService {

	// Create Rating

	public Rating createRating(Rating rating);

	// Get Rating by userId

	public List<Rating> getRatingByUserId(String userId);

	// Get Rating by hotelId

	public List<Rating> getRatingByHotelId(String hotelId);

	// Get All Rating

	public List<Rating> getAllRating();

	// Update Rating

	// Delete Rating
}
