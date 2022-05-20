package com.coderscampus.tristanassignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.tristanassignment13.domain.Address;
import com.coderscampus.tristanassignment13.domain.User;
import com.coderscampus.tristanassignment13.repository.AddressRepository;


@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	public Address save (Address address) {
		return addressRepo.save(address);
	}
	
	public Address findById(Long userId) {
		Optional<Address> addressOpt = addressRepo.findById(userId);
		return addressOpt.orElse(new Address());
	}
	
	public void createNewAddress(User user, Long userId) {
		if (user.getAddress() == null) {
			Address address = new Address();
			address.setUser(user);
			address.setUserId(user.getUserId());
			user.setAddress(address);
		}
	}
}
