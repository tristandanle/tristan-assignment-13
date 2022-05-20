package com.coderscampus.tristanassignment13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coderscampus.tristanassignment13.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}

