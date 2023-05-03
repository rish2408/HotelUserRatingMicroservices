package com.user.service.controller;

import java.util.List;

import com.user.service.entities.Rating;
import com.user.service.externalservices.RatingService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.user.service.entities.User;
import com.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RatingService ratingService;

	// Creating User

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	// Getting Single User
	int retryCount = 1;
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelRetry",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "ratingHotelRatelimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId) {
		logger.info("Retry count: {}", retryCount);
		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	// Creating fallback method

	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
		User user = User.builder()
				.email("dummy@gmail.com")
				.name("Dummy Name")
				.about("This User is created dummy because some service is down")
				.userId("13234")
				.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	// Getting All Users

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}

}
