package com.user.service.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.externalservices.HotelService;
import com.user.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User saveUser(User user) {
		
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public User getUser(String userId) {

		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server!! "+userId));

		// Calling Rating Service from User Service
		Rating[] ratingByUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingByUser);

		List<Rating> ratings = Arrays.stream(ratingByUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {


//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
//			logger.info("{}",forEntity.getStatusCode());
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}

//	@Override
//	public User getUser(String userId) {
//
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! " + userId));
//
//		// Calling Rating Service from User Service
//		Rating[] ratingByUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
//		logger.info("{}",ratingByUser);
//
//		List<Rating> ratingList = null;
//		if (ratingByUser != null) {
//			ratingList = Stream.of(ratingByUser)
//					.map(rating -> {
//						try {
////							ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
////							Hotel hotel = forEntity.getBody();
////							logger.info("{}",forEntity.getStatusCode());
//							
//							Hotel hotel = hotelService.getHotel(rating.getHotelId());
//
//							rating.setHotel(hotel);
//						} catch (HttpClientErrorException e) {
//							if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
//								logger.warn("Hotel with id {} is not found", rating.getHotelId());
//							} else {
//								logger.error("An error occurred while calling hotel service", e);
//							}
//						}
//						return rating;
//					})
//					.collect(Collectors.toList());
//		}
//
//		user.setRatings(ratingList);
//
//		return user;
//	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

}
