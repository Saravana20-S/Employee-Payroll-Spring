package com.bridgelabz.employeepayrolladdressgreeting.repository;

import com.bridgelabz.employeepayrolladdressgreeting.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
