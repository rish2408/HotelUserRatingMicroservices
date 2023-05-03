package com.hotel.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

	@Id
	@Column(name = "ID")
	private String hotelId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "LOCATION")
	private String location;
	@Column(name = "ABOUT " )
	private String about;
	
}
