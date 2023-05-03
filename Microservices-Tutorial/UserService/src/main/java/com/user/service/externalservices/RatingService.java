package com.user.service.externalservices;

import com.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

//	Create Rating
	@PostMapping("/ratings")
	public Rating createRating(Rating rating);

//	Update Rating
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

//	Delete Rating
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
}
