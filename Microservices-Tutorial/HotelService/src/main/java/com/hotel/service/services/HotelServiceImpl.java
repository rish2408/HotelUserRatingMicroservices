package com.hotel.service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.service.entity.Hotel;
import com.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.service.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel saveHotel(Hotel hotel) {

		String randomUserId = UUID.randomUUID().toString();
		hotel.setHotelId(randomUserId);
		return hotelRepository.save(hotel);

	}

	@Override
	public Hotel getHotel(String hotelId) {

		return hotelRepository.findById(hotelId).orElseThrow(
				() -> new ResourceNotFoundException("Hotel with given id is not found on server!! " + hotelId));
	}

	@Override
	public List<Hotel> getAllHotel() {

		return hotelRepository.findAll();
	}

}
