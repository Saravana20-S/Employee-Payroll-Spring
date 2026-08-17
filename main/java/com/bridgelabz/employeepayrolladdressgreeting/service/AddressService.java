package com.bridgelabz.employeepayrolladdressgreeting.service;


import com.bridgelabz.employeepayrolladdressgreeting.exception.AddressNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.AddressRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.AddressResponseDTO;

public interface AddressService {

    AddressResponseDTO addAddress(Long employeeId, AddressRequestDTO requestDTO);

    AddressResponseDTO getAddress(Long employeeId) throws AddressNotFound;

    AddressResponseDTO updateAddress(Long employeeId, AddressRequestDTO requestDTO) throws AddressNotFound;
}
