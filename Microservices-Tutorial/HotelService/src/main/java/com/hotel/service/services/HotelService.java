package com.hotel.service.services;

import java.util.List;

import com.hotel.service.entity.Hotel;

public interface HotelService {

	// Create Hotel

	public Hotel saveHotel(Hotel hotel);

	// Get Hotel

	public Hotel getHotel(String hotelId);

	// Get All Hotel

	public List<Hotel> getAllHotel();

	// Update Hotel

	// Delete Hotel

}
